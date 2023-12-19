package ru.son;

import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Integer> subTaskIDs;

    public Epic(String name, String description) {
        super(name, description, null);
        this.subTaskIDs = new ArrayList<>();
    }

    public Epic(String[] arr) {
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
                System.out.println("Epic constructor error");
                break;
        }

        String[] subtasksIDsString = arr[5].substring(1,arr[5].length()-1).split(", ");

        if(subtasksIDsString.length == 0) this.subTaskIDs = new ArrayList<>();
        else {
            this.subTaskIDs = new ArrayList<>();
            for(int i = 0; i < subtasksIDsString.length; i++)
                this.subTaskIDs.add(Integer.parseInt(subtasksIDsString[i]));
        }
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
//        return "ID in class = " + this.id + "\n" +
//                "epicName = " + this.name + "\n" +
//                "epicDescription = " + this.description + "\n" +
//                "epicStatus = " + this.status + "\n" +
//                "epicConsistsSubTasks = " + this.subTaskIDs + "\n";
        return this.id + "," + TaskType.EPIC + "," + this.name + "," + this.status + "," + this.description + "," + this.subTaskIDs + "\n";
    }
}
