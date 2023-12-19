package ru.son;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {

    private File fileForSaving;

    public FileBackedTaskManager(File fileForSaving) {
        this.fileForSaving = fileForSaving;
        System.out.println("Is path absolute? " + this.fileForSaving.isAbsolute());
        System.out.println("Path " + this.fileForSaving.getPath());
        System.out.println("Name " + this.fileForSaving.getName());
        System.out.println("Patent " + this.fileForSaving.getParent());
    }

    @Override
    public void createNewTask(Task newTask) {
        super.createNewTask(newTask);
        this.saveInFile();
    }

    @Override
    public void createNewEpic(Epic newEpic) {
        super.createNewEpic(newEpic);
        this.saveInFile();
    }

    @Override
    public void createNewSubTask(SubTask newSubTask) {
        super.createNewSubTask(newSubTask);
        this.saveInFile();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        this.saveInFile();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        this.saveInFile();
    }

    @Override
    public void deleteAllSubTasks() {
        super.deleteAllSubTasks();
        this.saveInFile();
    }

    @Override
    public Task getTaskByID(int id) {
        Task task = super.getTaskByID(id);
        saveInFile();
        return task;
    }

    @Override
    public Epic getEpicByID(int id) {
        Epic epic = super.getEpicByID(id);
        saveInFile();
        return epic;
    }

    @Override
    public SubTask getSubTaskByID(int id) {
        SubTask subTask = super.getSubTaskByID(id);
        this.saveInFile();
        return subTask;
    }

    @Override
    public void updateTask(Task newTask, int taskID) {
        super.updateTask(newTask, taskID);
        this.saveInFile();
    }

    @Override
    public void updateEpic(Epic newEpic, int epicID) {
        super.updateEpic(newEpic, epicID);
        this.saveInFile();
    }

    @Override
    public void updateSubTask(SubTask newSubTask, int subTaskID) {
        super.updateSubTask(newSubTask, subTaskID);
        this.saveInFile();
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        this.saveInFile();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        this.saveInFile();
    }

    @Override
    public void deleteSubTask(int id) {
        super.deleteSubTask(id);
        this.saveInFile();
    }

    public void saveInFile() {
        List<String> infForSaving = new ArrayList<>();
        infForSaving.add("id,type,name,status,description,№ subtask (for epic) or № epic (for subtusk)\n");

        for (int key : this.tasks.keySet())
            infForSaving.add(this.tasks.get(key).toString());
        for (int key : this.epics.keySet())
            infForSaving.add(this.epics.get(key).toString());
        for (int key : this.subTasks.keySet())
            infForSaving.add(this.subTasks.get(key).toString());
        infForSaving.add("\n");
        infForSaving.add(historyManager.toString());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileForSaving))) {
            for (int i = 0; i < infForSaving.size(); i++)
                bufferedWriter.write(infForSaving.get(i));
        } catch (IOException e) {
            System.out.println("ioex");
        }
    }

    public void loadFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileForSaving))) {
            String stringfromFile;
            while (!(stringfromFile = bufferedReader.readLine()).equals("")) {
                createTaskFromString(stringfromFile);
            }
            createHistoryFromString(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTaskFromString(String inputString) {
            String[] taskDetailsString = inputString.split(",", 6);
            if (taskDetailsString.length > 3 && (taskDetailsString[1].equals("TASK") || taskDetailsString[1].equals("EPIC") || taskDetailsString[1].equals("SUBTASK"))) {
                switch (taskDetailsString[1]) {
                    case ("TASK"):
                        this.tasks.put(Integer.parseInt(taskDetailsString[0]), new Task(taskDetailsString));
                        break;
                    case ("EPIC"):
                        this.epics.put(Integer.parseInt(taskDetailsString[0]), new Epic(taskDetailsString));
                        break;
                    case ("SUBTASK"):
                        this.subTasks.put(Integer.parseInt(taskDetailsString[0]), new SubTask(taskDetailsString));
                        break;
                    default:
                        System.out.println("createTaskFromString error");
                        break;
                }
            }
    }

    private void createHistoryFromString(String inputString) {
        String[] historyIDs = inputString.split(",");
        for(int i = 0; i < historyIDs.length; i++){
            if (this.tasks.containsKey(Integer.parseInt(historyIDs[i])))
                historyManager.addTask(this.tasks.get(Integer.parseInt(historyIDs[i])));
            else if (this.epics.containsKey(Integer.parseInt(historyIDs[i])))
                historyManager.addTask(this.epics.get(Integer.parseInt(historyIDs[i])));
            else if (this.subTasks.containsKey(Integer.parseInt(historyIDs[i])))
                historyManager.addTask(this.subTasks.get(Integer.parseInt(historyIDs[i])));
            else System.out.println("History paradox");
        }
    }
}
