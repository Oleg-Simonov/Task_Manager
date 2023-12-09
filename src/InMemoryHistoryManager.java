import java.util.ArrayList;
import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager{

    public LinkedList<Task> historyList;

    public InMemoryHistoryManager() {
        this.historyList = new LinkedList<>();
    }

    @Override
    public void addTask(Task task){
        if (this.historyList.size()<10) {
            this.historyList.add(task);
        }
        else {
            this.historyList.removeFirst();
            this.historyList.add(task);
        }
    }

    @Override
    public LinkedList<Task> getHistory(){
        System.out.println(this.historyList.size());
        return this.historyList;
    }
}
