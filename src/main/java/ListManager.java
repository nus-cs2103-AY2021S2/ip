package main.java;

import java.util.ArrayList;

public class ListManager extends Manager {
    ArrayList<Task> TaskArray;

    public ListManager(){
        TaskArray = new ArrayList<Task>();
    }

    public void addTask(String TaskDescription){
        TaskArray.add(new Task(TaskDescription));
    }

    public String returnTaskList(){
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');
        for (int i = 0; i < TaskArray.size(); i++) {

            Task currentTask = TaskArray.get(i);
            String temp = String.valueOf(i+1) + ". " + currentTask.description + '\n';
            sb.append(indentedString( temp ));
        }
        sb.append(horizontalLine());

        return sb.toString();
    }
}
