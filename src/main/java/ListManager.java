package main.java;

import java.util.ArrayList;

public class ListManager extends Manager {
    ArrayList<String> TaskArray;

    public ListManager(){
        TaskArray = new ArrayList<String>();
    }

    public void addTask(String Task){
        TaskArray.add(Task);
    }

    public String returnTaskList(){
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');
        for (int i = 0; i < TaskArray.size(); i++) {
            sb.append(indentedString( String.valueOf(i+1) + ". " + TaskArray.get(i) + '\n' ));
        }
        sb.append(horizontalLine());

        return sb.toString();
    }
}
