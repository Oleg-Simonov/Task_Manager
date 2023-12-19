package ru.son;

import java.util.*;

public interface TaskManager {

    public void createNewTask(Task newTask);

    public void createNewEpic(Epic newEpic);

    public void createNewSubTask(SubTask newSubTask);

    public HashMap<Integer, Task> getAllTasks();

    public HashMap<Integer, Epic> getAllEpics();

    public HashMap<Integer, SubTask> getSubTasks();

    public void deleteAllTasks();

    public void deleteAllEpics();

    public void deleteAllSubTasks();

    public Task getTaskByID(int id);

    public Epic getEpicByID(int id);

    public SubTask getSubTaskByID(int id);

    public void updateTask(Task newTask, int taskID);

    public void updateEpic(Epic newEpic, int epicID);

    public void updateSubTask(SubTask newSubTask, int subTaskID);

    public void deleteTask(int id);

    public void deleteEpic(int id);

    public void deleteSubTask(int id);

    public List<Task> history();

    public ArrayList<SubTask> getSubTasksOfEpic(int epicID);
}
