package ru.son;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected TaskStatus status;

    public Task() {
    }

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Task(String[] arr) {
        this.id = Integer.parseInt(arr[0]);
        this.name = arr[2];
        this.description = arr[4];
        switch (arr[3]){
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
                System.out.println("task constructor error");
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
//        return "ID in class = " + this.id + "\n" +
//                "taskName = " + this.name + "\n" +
//                "taskDescription = " + this.description + "\n" +
//                "taskStatus = " + this.status + "\n";
        return this.id + "," + TaskType.TASK + "," + this.name + "," + this.status + "," + this.description + "\n";
    }
}
