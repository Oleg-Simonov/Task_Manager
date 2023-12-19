package ru.son;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String taskName = "taskName";
        String taskDescription = "taskDescription";
        String epicName = "epicName";
        String epicDescription = "epicDescription";
        String subTaskName = "subTaskName";
        String subTaskDescription = "subTaskDescription";

        String separator = File.separator;
        File taskFile = new File("D:" + separator + "programming" + separator + "Java" + separator + "tasker" + separator + "My_tasks.csv");

        //TaskManager taskManager = Managers.getDefault();
        //TaskManager taskManager = new FileBackedTaskManager(taskFile);
        FileBackedTaskManager taskManager = new FileBackedTaskManager(taskFile);

        //для загрузки из файла
        /*taskManager.loadFromFile();
        showInConsole(taskManager);*/

        //для записи в файл
        for (int i = 0; i < 7; i++)
            taskManager.createNewTask(new Task(taskName + (i + 1), taskDescription + (i + 1), TaskStatus.NEW));

        for (int i = 0; i < 5; i++)
            taskManager.createNewEpic(new Epic(epicName + (i + 1), epicDescription + (i + 1)));

        for (Integer epicKey : taskManager.getAllEpics().keySet()) {
            for (int i = 0; i < 3; i++)
                taskManager.createNewSubTask(new SubTask(epicKey, subTaskName + (i + 1), subTaskDescription + (i + 1), TaskStatus.NEW));
        }

        taskManager.createNewEpic(new Epic("qwer_name", "qwer_desctript"));
        taskManager.createNewSubTask(new SubTask(28, "qwer_saba", "eto_qwer_saba", TaskStatus.DONE));

        taskManager.getTaskByID(3);
        taskManager.getTaskByID(5);
        taskManager.getTaskByID(7);
        taskManager.getEpicByID(10);
        taskManager.getEpicByID(11);
        taskManager.getSubTaskByID(19);
        taskManager.getSubTaskByID(20);
        taskManager.getSubTaskByID(21);
        taskManager.getSubTaskByID(19);
        taskManager.getSubTaskByID(29);

        taskManager.updateSubTask(new SubTask(10, "New saba", "New descript", TaskStatus.IN_PROGRESS), 19);



        /*show(taskManager);
        taskManager.deleteAllSubTasks();
        System.out.println("Subtasks clearing---------------------------------------------------------------");
        show(taskManager);
        System.out.println("add new subTask 1---------------------------------------------------------------");
        taskManager.createNewSubTask(new ru.son.SubTask(8, subTaskName + 1, subTaskDescription + 1, ru.son.TaskStatus.IN_PROGRESS));
        show(taskManager);
        System.out.println("add new subTask 2---------------------------------------------------------------");
        taskManager.createNewSubTask(new ru.son.SubTask(8, subTaskName + 2, subTaskDescription + 2, ru.son.TaskStatus.NEW));
        show(taskManager);
        System.out.println("updating subTask---------------------------------------------------------------");
        taskManager.updateSubTask(new ru.son.SubTask(8, "UpdatedSubtaskName", "UpdatedSubtaskDescription", ru.son.TaskStatus.NEW), 28);
        show(taskManager);
        System.out.println("deleting subTask, epic---------------------------------------------------------------");
        taskManager.deleteSubTask(28);
        taskManager.deleteEpic(10);
        show(taskManager);
        System.out.println("delete all tasks---------------------------------------------------------------");
        taskManager.deleteAllTasks();
        show(taskManager);*/
    }

    public static void showInConsole(FileBackedTaskManager taskManager) {
        for (Map.Entry<Integer, Task> enrtyInTasks : taskManager.getAllTasks().entrySet()) {
            System.out.println("ID in map = " + enrtyInTasks.getKey());
            System.out.println("\tID in class = " + enrtyInTasks.getValue().getId());
            System.out.println("\ttaskName = " + enrtyInTasks.getValue().getName());
            System.out.println("\ttaskDescription = " + enrtyInTasks.getValue().getDescription());
            System.out.println("\ttaskStatus = " + enrtyInTasks.getValue().getStatus());
        }

        for (Map.Entry<Integer, Epic> enrtyInEpics : taskManager.getAllEpics().entrySet()) {
            System.out.println("ID in map = " + enrtyInEpics.getKey());
            System.out.println("\tID in class = " + enrtyInEpics.getValue().getId());
            System.out.println("\tepicName = " + enrtyInEpics.getValue().getName());
            System.out.println("\tepicDescription = " + enrtyInEpics.getValue().getDescription());
            System.out.println("\tepicStatus = " + enrtyInEpics.getValue().getStatus());
            System.out.println("\thas subTasks (ID) " + enrtyInEpics.getValue().getSubTaskIDs());

            for (Map.Entry<Integer, SubTask> enrtyInSubTasks : taskManager.getSubTasks().entrySet()) {
                if (enrtyInSubTasks.getValue().getEpicID() == enrtyInEpics.getValue().getId()) {
                    System.out.println("\t\tID in map = " + enrtyInSubTasks.getKey());
                    System.out.println("\t\t\tID in class = " + enrtyInSubTasks.getValue().getId());
                    System.out.println("\t\t\tsubTaskName = " + enrtyInSubTasks.getValue().getName());
                    System.out.println("\t\t\tsubTaskDescription = " + enrtyInSubTasks.getValue().getDescription());
                    System.out.println("\t\t\tsubTaskStatus = " + enrtyInSubTasks.getValue().getStatus());
                    System.out.println("\t\t\tbelongs to epic (epicID) = " + enrtyInSubTasks.getValue().getEpicID());
                }
            }
        }
        System.out.println();
        for(Task task :taskManager.historyManager.getHistory()){
            System.out.print(task.toString());
        }
        //System.out.println(taskManager.historyManager.getHistory().toString());
    }
}
