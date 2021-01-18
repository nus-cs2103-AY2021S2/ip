package main.java;

import java.util.ArrayList;

public class ListManager extends Manager {
    ArrayList<Task> TaskArray;
    int numberOfTasks;

    public ListManager(){
        TaskArray = new ArrayList<Task>();
        numberOfTasks = 0;
    }

    public String addTask(String userinput){
        Task newTask;

        if(userinput.length() > 5 && userinput.substring(0, 5).equals("todo ")){
            newTask = new ToDo(userinput.substring(5));

        }else if(userinput.length() > 6 && userinput.substring(0,6).equals("event ")) {
            String[] arr = userinput.substring(6).split(" /at ");
            newTask = new Event(arr[0], arr[1]);

        }else if(userinput.length() > 9 && userinput.substring(0, 9).equals("deadline ")){
            String[] arr = userinput.substring(9).split(" /by ");
            newTask = new Deadline(arr[0], arr[1]);

        }else{
            newTask = new Task(userinput);
            TaskArray.add(newTask);
            return defaultFormatting("added: " + userinput);
        }
        TaskArray.add(newTask);
        numberOfTasks = numberOfTasks + 1;
        String temp = "Got it. I've added this task:\n       " + newTask.toString() + "\n" + getNumberOfTasks();
        return defaultFormatting(temp);
    }

    public String goodbyeLine(){
        return defaultFormatting("Bye. Hope to see you again soon!");
    }

    public String welcomeLine(){
        return defaultFormatting("Hello! I'm Duke\n" + "     What can I do for you?");
    }

    public String getNumberOfTasks(){
        return "     Now you have " + String.valueOf(numberOfTasks)+ " tasks in the list.";
    }

    public String checkTaskAsDone(int number){

        Task currentTask = TaskArray.get(number - 1);
        currentTask.changeTaskToDone();

        return defaultFormatting("Nice! I've marked this task as done:\n       " + currentTask.toString());
    }

    public String returnTaskList(){
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine()).append('\n');

        sb.append(indentedString("Here are the tasks in your list:\n"));
        for (int i = 0; i < TaskArray.size(); i++) {

            Task currentTask = TaskArray.get(i);

            String numberIndicator =  String.valueOf(i+1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(indentedString( temp ));
        }
        sb.append(horizontalLine());

        return sb.toString();
    }
}
