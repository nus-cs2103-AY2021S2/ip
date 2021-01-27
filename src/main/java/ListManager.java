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
        return defaultFormatting("Hello! I'm Duke\n" + padSpaces("What can I do for you?", 5));
    }

    public String goodbyeLine(){
        return defaultFormatting("Bye. Hope to see you again soon!");
    }

    public String getNumberOfTasks(){
        return padSpaces("Now you have " + String.valueOf(numberOfTasks)+ " tasks in the list.", 5);
    }

    public String addTask(String userInput) throws DukeException{
        Task newTask;

        if(checkStringStartingEquals(userInput, "todo")){

            if (checkStringEquals(userInput, "todo")){
                throw new DukeException(defaultFormatting("Error! The description of a todo cannot be empty!"));
            }else{
                newTask = new ToDo(userInput.substring(5));
            }

        }else if(checkStringStartingEquals(userInput, "event")) {

            if (checkStringEquals(userInput, "event")){
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
        }else if(checkStringStartingEquals(userInput, "deadline")){

            if (checkStringEquals(userInput, "deadline")){
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
        String temp = "Got it. I've added this task:\n" + padSpaces(newTask.toString(), 7) +
                "\n" + getNumberOfTasks();
        return defaultFormatting(temp);
    }

    public String checkTaskAsDone(int number) throws DukeException{
        if (number >= 1 && number<= TaskArray.size()) {
            Task currentTask = TaskArray.get(number - 1);
            currentTask.changeTaskToDone();
            return defaultFormatting("Nice! I've marked this task as done:\n" +
                    padSpaces(currentTask.toString(),7));
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

            String numberIndicator =  (i+1) + ".";
            String temp = numberIndicator + currentTask.toString() + '\n';

            sb.append(indentedString( temp ));
        }
        sb.append(horizontalLine());

        return sb.toString();
    }

    public String handleAllUserInput(String userInput) throws DukeException{
        if (checkStringEquals(userInput,"list")){
            return this.returnTaskList();
        }else{
            try {
                String outputString = this.handleTaskRelatedUserInput(userInput);
                return outputString;
            }catch(DukeException e){
                throw e;
            }
        }
    }

    public String handleTaskRelatedUserInput(String userInput) throws DukeException{

        if(checkStringStartingEquals(userInput, "done")){
            // done block
            if (checkStringEquals(userInput, "done")){
                throw new DukeException(defaultFormatting("Error! Please indicate the task " +
                        "which is done by its number on the list"));
            }else {
                try {
                    int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                    String outputString = this.checkTaskAsDone(taskInt);
                    return outputString;
                } catch (NumberFormatException e) {
                    throw new DukeException(defaultFormatting("Error! You must give a number " +
                            "corresponding to a task on the list to check as done"));
                } catch (DukeException dukeException) {
                    throw dukeException;
                }
            }
        }else if(checkStringStartingEquals(userInput, "delete")){
            // delete block
            if (checkStringEquals(userInput, "delete")){
                throw new DukeException(defaultFormatting("Error! Please indicate the task " +
                        "which want to delete by its number on the list"));
            }else {
                try {
                    int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                    String outputString = this.deleteTask(taskInt);
                    return outputString;
                } catch (NumberFormatException e) {
                    throw new DukeException(defaultFormatting("Error! You must give a number " +
                            "corresponding to a task on the list to delete"));
                } catch (DukeException dukeException) {
                    throw dukeException;
                }
            }
        }else{
            // event deadline to-do block
            try{
                String outputString = this.addTask(userInput);
                return outputString;
            }catch (DukeException dukeException){
                throw dukeException;
            }
        }
    }

    public String deleteTask(int taskInt) throws DukeException{
        if (taskInt >= 1 && taskInt <= TaskArray.size()){

            Task todelete = TaskArray.get(taskInt - 1);
            TaskArray.remove(taskInt - 1);
            numberOfTasks = numberOfTasks - 1;

            String a = "Noted. I've removed this task:\n" + padSpaces(todelete.toString(), 7) +
                    "\n" + getNumberOfTasks();

            return defaultFormatting(a);
        }else{
            throw new DukeException(defaultFormatting("Error! Please make sure the " +
                    "number given has a corresponding task!"));
        }
    }

    private static boolean checkStringStartingEquals(String userInput, String stringToCheck){
        return userInput.length() >= stringToCheck.length() &&
                userInput.substring(0, stringToCheck.length()).equals(stringToCheck);
    }

    private static boolean checkStringEquals(String userInput, String stringToCheck){
        return userInput.equals(stringToCheck) || userInput.equals(stringToCheck + " ");
    }
}
