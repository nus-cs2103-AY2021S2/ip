package main.java;

import java.util.ArrayList;

public class ListManager extends Manager {
    ArrayList<Task> TaskArray;

    public ListManager(){
        TaskArray = new ArrayList<Task>();
    }

    public String addTask(String TaskDescription){
        TaskArray.add(new Task(TaskDescription));
        return defaultFormatting("added: " + TaskDescription);
    }

    public String goodbyeLine(){
        return defaultFormatting("Bye. Hope to see you again soon!");
    }

    public String welcomeLine(){
        return defaultFormatting("Hello! I'm Duke\n" + "     What can I do for you?");
    }

    public String checkTaskAsDone(int number){

        Task currentTask = TaskArray.get(number - 1);
        currentTask.changeTaskToDone();

        return defaultFormatting("Nice! I've marked this task as done:\n       " + currentTask.returnDescription());
    }

    public String returnTaskList(){
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(indentedString("Here are the tasks in your list:\n"));
        for (int i = 0; i < TaskArray.size(); i++) {

            Task currentTask = TaskArray.get(i);

            String numberIndicator =  String.valueOf(i+1) + ".";
            String temp = numberIndicator + currentTask.returnDescription() + '\n';

            sb.append(indentedString( temp ));
        }
        sb.append(horizontalLine());

        return sb.toString();
    }
}
