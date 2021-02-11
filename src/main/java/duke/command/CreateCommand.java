package duke.command;

import duke.*;

public class CreateCommand {

    /*public static String addTask(Task newTask) {
        Parser.getTaskList().add(newTask);
        return ("Got it. I've added this task:" + "\n" + newTask.toString()
                + "\n" + "Now you have " + Parser.getTaskList().size() + " tasks in the list.");
    }*/

    public static EventTask createEvent(String[] inputArray) {
        String description = "";
        String time = "";
        int divider = 0;
        for (int i = 1; i < inputArray.length; ++i) {
            if (inputArray[i].equals("/at")) {
                divider = i + 1;
                break;
            }
            description = description + " " + inputArray[i];
        }
        for (int i = divider; i < inputArray.length; ++i) {
            time = time + " " + inputArray[i];
        }
        description = description.trim();
        time = time.trim();
        return new EventTask(description, time);
    }

    public static DeadlineTask createDeadline(String[] inputArray) {
        String description = "";
        String time = "";
        int divider = 0;
        for (int i = 1; i < inputArray.length; ++i) {
            if (inputArray[i].equals("/by")) {
                divider = i + 1;
                break;
            }
            description = description + " " + inputArray[i];
        }
        for (int i = divider; i < inputArray.length; ++i) {
            time = time + " " + inputArray[i];
        }
        description = description.trim();
        time = time.trim();
        return new DeadlineTask(description, time);
    }

    public static ToDoTask createToDo(String[] inputArray) {
        String description = "";
        for (int i = 1; i < inputArray.length; ++i) {
            description = description + " " + inputArray[i];
        }
        description = description.trim();
        return new ToDoTask(description);
    }

    public static String runCommand(String input) throws DukeException, Exception {
        String[] spiltInput = input.split("\\s+");
        String typeOfEvent = spiltInput[0];
        switch(typeOfEvent) {
        case "deadline":
            return Parser.addTask(createDeadline(spiltInput));
        case "todo":
            return Parser.addTask(createToDo(spiltInput));
        case "event":
            return Parser.addTask(createEvent(spiltInput));
        default:
            return "";
        }
    }
}
