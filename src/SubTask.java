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
}
