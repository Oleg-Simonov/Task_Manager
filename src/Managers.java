public class Managers {
    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistry(){
        return new InMemoryHistoryManager();
    }
}
