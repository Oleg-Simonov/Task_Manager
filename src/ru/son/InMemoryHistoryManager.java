package ru.son;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> historyList = new LinkedList<>();

    @Override
    public void addTask(Task task){
        if (this.historyList.size()<10) {
            this.historyList.add(task);
        }
        else {
            this.historyList.remove(0);
            this.historyList.add(task);
        }
    }

    @Override
    public void removeTask(int id){

    }

    @Override
    public List<Task> getHistory(){
        return historyList;
    }

    @Override
    public String toString(){
        StringBuilder historyListInString = new StringBuilder("");
            for(int i = 0; i < historyList.size(); i ++) {
                if (i != historyList.size() - 1) historyListInString.append(historyList.get(i).getId() + ",");
                else historyListInString.append(historyList.get(i).getId());
            }

        return historyListInString.toString();
    }


}
