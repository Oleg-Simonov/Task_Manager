public class SubTask extends Task{
    private int epicID;

    public SubTask() {
        super();
    }
    public SubTask(int epicID, String name, String description, TaskStatus status) {
        super(name, description, status);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString(){
        return "ID in class = " + this.id + "\n" +
                "subTaskName = " + this.name + "\n" +
                "subTaskDescription = " + this.description + "\n" +
                "subTaskStatus = " + this.status + "\n" +
                "subTaskbelongsToEpic = " + this.epicID + "\n";
    }
}
