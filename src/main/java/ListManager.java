package main.java;

import java.util.ArrayList;

public class ListManager extends Manager {
    protected ArrayList<Task> TaskArray;
    protected int numberOfTasks;

    public ListManager(){
        TaskArray = new ArrayList<Task>();
        numberOfTasks = 0;
    }

    public String welcomeLine(){
        return defaultFormatting("Hello! I'm Duke\n" + "     What can I do for you?");
    }

    public String goodbyeLine(){
        return defaultFormatting("Bye. Hope to see you again soon!");
    }

    public String getNumberOfTasks(){
        return "     Now you have " + String.valueOf(numberOfTasks)+ " tasks in the list.";
    }

    public String addTask(String userInput) throws DukeException{
        Task newTask;

        if(userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")){

            if (userInput.equals("todo") || userInput.equals("todo ")){
                throw new DukeException(defaultFormatting("Error! The description of a todo cannot be empty!"));
            }else{
                newTask = new ToDo(userInput.substring(5));
            }

        }else if(userInput.length() >= 5 && userInput.substring(0,5).equals("event")) {

            if (userInput.equals("event") || userInput.equals("event ")){
                throw new DukeException(defaultFormatting("Error! The description of a event cannot be empty!"));
            }else {
                if (userInput.contains(" /at ")) {
                    String[] arr = userInput.substring(6).split(" /at ");
                    String eventDescription = arr[0];
                    String descriptionAt = arr[1];
                    newTask = new Event(eventDescription, descriptionAt);
                }else{
                    throw new DukeException(defaultFormatting("Error! Please provide where and when the" +
                            " event will take place after /at."));
                }
            }
        }else if(userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")){

            if (userInput.equals("deadline") || userInput.equals("deadline ")){
                throw new DukeException(defaultFormatting("Error! The description of a deadline cannot be empty!"));
            }else {
                if (userInput.contains(" /by ")) {
                    String[] arr = userInput.substring(9).split(" /by ");
                    String deadlineDescription = arr[0];
                    String descriptionBy = arr[1];
                    newTask = new Deadline(deadlineDescription, descriptionBy);
                }else{
                    throw new DukeException(defaultFormatting("Error! Please provide when the deadline " +
                            "will be due after /by."));
                }
            }
        }else{
            throw new DukeException(defaultFormatting("I'm sorry, but I don't know what that means"));
        }

        TaskArray.add(newTask);
        numberOfTasks = numberOfTasks + 1;
        String temp = "Got it. I've added this task:\n       " + newTask.toString() + "\n" + getNumberOfTasks();
        return defaultFormatting(temp);
    }

    public String checkTaskAsDone(int number) throws DukeException{

        if (number >= 1 && number<= TaskArray.size()) {
            Task currentTask = TaskArray.get(number - 1);
            currentTask.changeTaskToDone();
            return defaultFormatting("Nice! I've marked this task as done:\n       " + currentTask.toString());
        }else{
            throw new DukeException(defaultFormatting("Error! Please make sure the " +
                    "number given has a corresponding task!"));
        }
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

    public String handleTaskRelatedUserInput(String userInput) throws DukeException{
        if(userInput.length() >= 4 && userInput.substring(0, 4).equals("done")){

            if (userInput.equals("done") || userInput.equals("done ")){
                throw new DukeException(defaultFormatting("Error! Please indicate the task " +
                        "which is done by its number on the list"));
            }else {

                try {
                    int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                    String outputString = this.checkTaskAsDone(taskInt);
                    return outputString;
                } catch (NumberFormatException e) {
                    throw new DukeException(defaultFormatting("Error! You must give a number " +
                            "corresponding to a task on the list"));
                } catch (DukeException e) {
                    throw e;
                }
            }
        }else{
            try{
                String outputString = this.addTask(userInput);
                return outputString;
            }catch (DukeException e){
                throw e;
            }
        }

    }
}
