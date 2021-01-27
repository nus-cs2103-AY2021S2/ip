package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ListManager extends Manager {
    protected ArrayList<Task> TaskArray;
    protected int numberOfTasks;
    protected String pathOfDataFile;

    public ListManager(String pathOfDataFile){
        this.TaskArray = new ArrayList<Task>();
        this.numberOfTasks = 0;
        this.pathOfDataFile = pathOfDataFile;
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

            int length = "todo".length();
            if (checkStringEquals(userInput, "todo")){
                throw new DukeException(defaultFormatting("Error! The description of a todo cannot be empty!"));
            }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
                throw new DukeException(defaultFormatting("Error! Please include a space after todo. " +
                        "[eg. todo sleep]"));
            }else{
                newTask = new ToDo(userInput.substring(length + 1));
            }

        }else if(checkStringStartingEquals(userInput, "event")) {

            int length = "event".length();
            if (checkStringEquals(userInput, "event")){
                throw new DukeException(defaultFormatting("Error! The description of a event cannot be empty!"));
            }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
                throw new DukeException(defaultFormatting("Error! Please include a space after event. " +
                        "[eg. event read /at 9pm]"));
            }else {
                if (userInput.contains(" /at ")) {
                    String[] arr = userInput.substring(length + 1).split(" /at ");
                    String eventDescription = arr[0];
                    String descriptionAt = arr[1];
                    newTask = new Event(eventDescription, descriptionAt);
                }else{
                    throw new DukeException(defaultFormatting("Error! Please provide where and when the" +
                            " event will take place after /at. [eg event read /at 9pm]"));
                }
            }
        }else if(checkStringStartingEquals(userInput, "deadline")){
            int length = "deadline".length();
            if (checkStringEquals(userInput, "deadline")){
                throw new DukeException(defaultFormatting("Error! The description of a deadline cannot be empty!"));
            }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
                throw new DukeException(defaultFormatting("Error! Please include a space after deadline. " +
                        "[eg. deadline submit work /at 10am]"));
            }else {
                if (userInput.contains(" /by ")) {
                    String[] arr = userInput.substring(length+1).split(" /by ");
                    String deadlineDescription = arr[0];
                    String descriptionBy = arr[1];

                    try {
                        newTask = new Deadline(deadlineDescription, descriptionBy);
                    }catch (TaskException e){
                        throw new DukeException(defaultFormatting(e.getMessage()));
                    }
                }else{
                    throw new DukeException(defaultFormatting("Error! Please provide when the deadline " +
                            "will be due after /by. [eg. deadline submit work /at 10am]"));
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
        String outputString;
        if(checkStringStartingEquals(userInput, "done")){
            // done block
            int length = "done".length();
            if (checkStringEquals(userInput, "done")){
                throw new DukeException(defaultFormatting("Error! Please indicate the task " +
                        "which is done by its number on the list"));
            }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
                throw new DukeException(defaultFormatting("Error! Please include a space after done. " +
                        "[eg. done 1]"));
            }else {
                try {
                    int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                    outputString = this.checkTaskAsDone(taskInt);
                } catch (NumberFormatException e) {
                    throw new DukeException(defaultFormatting("Error! You must give a number " +
                            "corresponding to a task on the list to check as done"));
                } catch (DukeException dukeException) {
                    throw dukeException;
                }
            }
        }else if(checkStringStartingEquals(userInput, "delete")){
            // delete block
            int length = "delete".length();
            if (checkStringEquals(userInput, "delete")){
                throw new DukeException(defaultFormatting("Error! Please indicate the task " +
                        "which want to delete by its number on the list"));
            }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
                throw new DukeException(defaultFormatting("Error! Please include a space after delete. " +
                        "[eg. delete 1]"));
            }else {
                try {
                    int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                    outputString = this.deleteTask(taskInt);
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
                outputString = this.addTask(userInput);
            }catch (DukeException dukeException){
                throw dukeException;
            }
        }

        saveToFile();
        return outputString;
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

    private void saveToFile(){
        try {
            Writer fileWriter = new FileWriter("data/dukedata.txt", false);
            for (int i = 0; i < TaskArray.size(); i++) {
                String line = TaskArray.get(i).toSaveFormat();
                fileWriter.write(line + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreDataFromDataFile() throws FileNotFoundException{
        try{
            File dukeData = new File(pathOfDataFile);
            Scanner dataReader = new Scanner(dukeData);

            while (dataReader.hasNextLine()){
                String data = dataReader.nextLine();
                String[] arr = data.split("\\|");

                String taskSymbol = arr[0];
                int doneInt = Integer.valueOf(arr[1]);
                String description = arr[2];

                if (taskSymbol.equals("T")){
                    TaskArray.add(new ToDo(description, doneInt));
                    numberOfTasks++;
                }else if(taskSymbol.equals("D")){
                    TaskArray.add(new Deadline(description, arr[3], doneInt));
                    numberOfTasks++;
                }else if(taskSymbol.equals("E")){
                    TaskArray.add(new Event(description, arr[3], doneInt));
                    numberOfTasks++;
                }

            }
            dataReader.close();

            System.out.println(defaultFormatting("Data Successfuly Restored"));
        }catch(FileNotFoundException e){
            throw e;
        }
    }
}
