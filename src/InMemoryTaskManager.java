import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class InMemoryTaskManager implements TaskManager {
    private int id;
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    //private LinkedList<Task> historyList;
    HistoryManager historyManager;

    InMemoryTaskManager(){
        this.id = 0;
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
        //this.historyList = new LinkedList<>();
        this.historyManager = Managers.getDefaultHistry();
    }

    @Override
    public void createNewTask(Task newTask){
        this.id++;
        newTask.setId(this.id);
        this.tasks.put(this.id, newTask);
    }

    @Override
    public void createNewEpic(Epic newEpic){
        this.id++;
        newEpic.setId(this.id);
        this.epics.put(this.id, newEpic);
        updateEpicStatus(newEpic.getId());
    }

    @Override
    public void createNewSubTask(SubTask newSubTask){
        this.id++;
        newSubTask.setId(this.id);
        Epic epicOfNewSubtask = this.epics.get(newSubTask.getEpicID());
        epicOfNewSubtask.addSubTask(this.id); //добавление подзадачи в соответствующий эпик
        this.subTasks.put(this.id, newSubTask);
        updateEpicStatus(newSubTask.getEpicID());
    }

    @Override
    public HashMap<Integer, Task> getAllTasks() {
        return this.tasks;
    }

    @Override
    public HashMap<Integer, Epic> getAllEpics() {
        return this.epics;
    }

    @Override
    public HashMap<Integer, SubTask> getSubTasks() {
        return this.subTasks;
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        subTasks.clear();
        for (int key : this.epics.keySet()) {
            this.epics.get(key).getSubTaskIDs().clear();
            updateEpicStatus(this.epics.get(key).getId());
        }
    }

    @Override
    public Task getTaskByID(int id) {
        /*if (this.historyList.size()<10)
            this.historyList.add(this.tasks.get(id));
        else {
            this.historyList.removeFirst();
            this.historyList.add(this.tasks.get(id));
        }*/
        historyManager.addTask(this.tasks.get(id));
        return this.tasks.get(id);
    }

    @Override
    public Epic getEpicByID(int id) {
        /*if (this.historyList.size()<10)
            this.historyList.add(this.epics.get(id));
        else {
            this.historyList.removeFirst();
            this.historyList.add(this.epics.get(id));
        }*/
        historyManager.addTask(this.epics.get(id));
        return this.epics.get(id);
    }

    @Override
    public SubTask getSubTaskByID(int id) {
       /*if (this.historyList.size()<10)
            this.historyList.add(this.subTasks.get(id));
        else {
            this.historyList.removeFirst();
            this.historyList.add(this.subTasks.get(id));
        }*/
        historyManager.addTask(this.subTasks.get(id));
        return this.subTasks.get(id);
    }

    @Override
    public boolean updateTask(Task newTask, int taskID) {
        if(this.tasks.containsKey(taskID)) {
            this.tasks.put(taskID, newTask);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEpic(Epic newEpic, int epicID) {
        if(this.epics.containsKey(epicID)) {
            this.epics.put(epicID, newEpic);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSubTask(SubTask newSubTask, int subTaskID) {
        if(newSubTask.getEpicID() == this.subTasks.get(subTaskID).getEpicID()) //не даем менять эпик
        {
            if(this.subTasks.containsKey(subTaskID)) {
                this.subTasks.put(subTaskID, newSubTask);
                updateEpicStatus(newSubTask.getEpicID());
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteTask(int id) {
        if(this.tasks.containsKey(id)) {
            this.tasks.remove(id);
        }
    }

    @Override
    public void deleteEpic(int id) {
        if(this.epics.containsKey(id)) {
            this.epics.remove(id);
        }
    }

    @Override
    public void deleteSubTask(int id) {
        if(this.subTasks.containsKey(id)) {
            int epicIDOfDeletingSubtask = this.subTasks.get(id).getEpicID();
            this.epics.get(epicIDOfDeletingSubtask).getTaskIDs().remove(Integer.valueOf(id));
            this.subTasks.remove(id);
        }
    }

    @Override
    public ArrayList<SubTask> getSubTasksOfEpic(int epicID) {
        ArrayList<SubTask> subTasksOfEpic = new ArrayList<>();
        ArrayList<Integer> IDsOfSubtasksOfEpic = this.epics.get(epicID).getSubTaskIDs();
        for (int i = 0; i < IDsOfSubtasksOfEpic.size(); i++)
                subTasksOfEpic.add(this.subTasks.get(i));
        return subTasksOfEpic;
    }

    @Override
    public LinkedList<Task> history() {
        return historyManager.getHistory();
    }

    private void updateEpicStatus(int epicID) {
        ArrayList<Integer> taskIDs = epics.get(epicID).getSubTaskIDs();
        if(taskIDs.size() == 0 || areAllSubTasksNew(taskIDs)) {
            epics.get(epicID).setStatus(TaskStatus.NEW);
        }
        else if(areAllSubTasksDone(taskIDs)){
            epics.get(epicID).setStatus(TaskStatus.DONE);
        }
        else {
            epics.get(epicID).setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    private boolean areAllSubTasksNew(ArrayList taskIDs){
        for (int i = 0; i < taskIDs.size(); i++)
            if(this.subTasks.get(taskIDs.get(i)).getStatus() != TaskStatus.NEW)
                return false;
        return true;
    }

    private boolean areAllSubTasksDone(ArrayList taskIDs){
        for (int i = 0; i < taskIDs.size(); i++)
            if(this.subTasks.get(taskIDs.get(i)).getStatus() != TaskStatus.DONE)
                return false;
        return true;
    }
}
