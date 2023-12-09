public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected TaskStatus status;

    public Task() {
    }

    public Task(String name, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
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
        return "ID in class = " + this.id + "\n" +
                "taskName = " + this.name + "\n" +
                "taskDescription = " + this.description + "\n" +
                "taskStatus = " + this.status + "\n";
    }
}
