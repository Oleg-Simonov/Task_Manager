package ru.son;

public class SubTask extends Task{
    private int epicID;

    public SubTask() {
        super();
    }
    public SubTask(int epicID, String name, String description, TaskStatus status) {
        super(name, description, status);
        this.epicID = epicID;
    }

    public SubTask(String[] arr) {
        this.id = Integer.parseInt(arr[0]);
        this.name = arr[2];
        this.description = arr[4];
        switch (arr[3]) {
            case "NEW":
                this.status = TaskStatus.NEW;
                break;
            case "IN_PROGRESS":
                this.status = TaskStatus.IN_PROGRESS;
                break;
            case "DONE":
                this.status = TaskStatus.DONE;
                break;
            default:
                System.out.println("SubTask constructor error");
                break;
        }
        this.epicID = Integer.parseInt(arr[5]);
    }

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString(){
//        return "ID in class = " + this.id + "\n" +
//                "subTaskName = " + this.name + "\n" +
//                "subTaskDescription = " + this.description + "\n" +
//                "subTaskStatus = " + this.status + "\n" +
//                "subTaskbelongsToEpic = " + this.epicID + "\n";
        return this.id + "," + TaskType.SUBTASK + "," + this.name + "," + this.status + "," + this.description + "," + this.epicID + "\n";
    }
}
