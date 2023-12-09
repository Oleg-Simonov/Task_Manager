import java.util.LinkedList;

public interface HistoryManager {
    void addTask(Task task);
    LinkedList getHistory();
}
