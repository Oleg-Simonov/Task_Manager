import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Integer> subTaskIDs;

    public Epic(String name, String description) {
        super(name, description, null);
        this.subTaskIDs = new ArrayList<>();
    }

    public void addSubTask(int subTaskID){
        this.subTaskIDs.add(subTaskID);
    }

    public ArrayList<Integer> getTaskIDs(){
        return this.subTaskIDs;
    }

    public ArrayList<Integer> getSubTaskIDs() {
        return subTaskIDs;
    }

    public void setSubTaskIDs(ArrayList<Integer> subTaskIDs) {
        this.subTaskIDs = subTaskIDs;
    }

    @Override
    public String toString(){
        return "ID in class = " + this.id + "\n" +
                "epicName = " + this.name + "\n" +
                "epicDescription = " + this.description + "\n" +
                "epicStatus = " + this.status + "\n" +
                "epicConsistsSubTasks = " + this.subTaskIDs + "\n";
    }
}
