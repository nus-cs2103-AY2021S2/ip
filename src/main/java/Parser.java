package main.java;

import java.util.ArrayList;

public class Parser {

    public Parser(){
    }

    private static boolean checkStringStartingEquals(String userInput, String stringToCheck){
        return userInput.length() >= stringToCheck.length() &&
                userInput.substring(0, stringToCheck.length()).equals(stringToCheck);
    }

    private static boolean checkStringEquals(String userInput, String stringToCheck){
        return userInput.equals(stringToCheck) || userInput.equals(stringToCheck + " ");
    }

    public boolean equalsToList(String userInput){
        return checkStringEquals(userInput,"list");
    }

    public boolean equalsToDone(String userInput){
        return checkStringStartingEquals(userInput, "done");
    }

    public int parseDoneCommand(String userInput) throws DukeException{
        int length = "done".length();
        if (checkStringEquals(userInput, "done")){
            throw new DukeException("Error! Please indicate the task " +
                    "which is done by its number on the list");
        }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
            throw new DukeException("Error! Please include a space after done. " +
                    "[eg. done 1]");
        }else{
            try {
                int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                return taskInt;
            } catch (NumberFormatException e) {
                throw new DukeException("Error! You must give a number corresponding " +
                        "to a task on the list to check as done");
            }
        }
    }

    public boolean equalsToDelete(String userInput){
        return checkStringStartingEquals(userInput, "delete");
    }

    public int parseDeleteCommand(String userInput) throws DukeException{

        int length = "delete".length();
        if (checkStringEquals(userInput, "delete")){
            throw new DukeException("Error! Please indicate the task which want to delete by its number on the list");
        }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
            throw new DukeException("Error! Please include a space after delete. " +
                    "[eg. delete 1]");
        }else {
            try {
                int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                return taskInt;
            } catch (NumberFormatException e) {
                throw new DukeException("Error! You must give a number corresponding to a task on the list to delete");
            }
        }
    }

    public boolean equalsToToDo(String userInput){
        return checkStringStartingEquals(userInput, "todo");
    }

    public String parseToDoCommand(String userInput) throws DukeException{
        int length = "todo".length();
        if (checkStringEquals(userInput, "todo")){
            throw new DukeException("Error! The description of a todo cannot be empty!");
        }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
            throw new DukeException("Error! Please include a space after todo. [eg. todo sleep]");
        }else{
            return userInput.substring(length + 1);
        }
    }

    public boolean equalsToEvent(String userInput) {
        return checkStringStartingEquals(userInput, "event");
    }

    public ArrayList<String> parseEventCommand(String userInput) throws DukeException{
        int length = "event".length();
        if (checkStringEquals(userInput, "event")){
            throw new DukeException("Error! The description of a event cannot be empty!");
        }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
            throw new DukeException("Error! Please include a space after event. [eg. event read /at 9pm]");
        }else {
            if (userInput.contains(" /at ")) {
                String[] arr = userInput.substring(length + 1).split(" /at ");
                String eventDescription = arr[0];
                String descriptionAt = arr[1];

                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(eventDescription);
                tempArr.add(descriptionAt);

                return tempArr;
            }else{
                throw new DukeException("Error! Please provide where and when the" +
                        " event will take place after /at. [eg event read /at 9pm]");
            }
        }
    }

    public boolean equalsToDeadline(String userInput) {
        return checkStringStartingEquals(userInput, "deadline");
    }

    public ArrayList<String> parseDeadlineCommand(String userInput) throws DukeException{
        int length = "deadline".length();
        if (checkStringEquals(userInput, "deadline")){
            throw new DukeException("Error! The description of a deadline cannot be empty!");
        }else if(!checkStringEquals(userInput.substring(length,length+1), " ")){
            throw new DukeException("Error! Please include a space after deadline. " +
                    "[eg. deadline submit work /by 2020-03-20]");
        }else {
            if (userInput.contains(" /by ")) {
                String[] arr = userInput.substring(length+1).split(" /by ");
                String deadlineDescription = arr[0];
                String descriptionBy = arr[1];

                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(deadlineDescription);
                tempArr.add(descriptionBy);

                return tempArr;
            }else{
                throw new DukeException("Error! Please provide when the deadline will be due after /by. " +
                        "[eg. deadline submit work /by 2020-03-20]");
            }
        }

    }
}