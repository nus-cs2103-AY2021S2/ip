package duke.command;

import duke.DeadlineTask;
import duke.EventTask;
import duke.Parser;
import duke.ToDoTask;

/**
 * handles the creation of the different task objects
 */
public class CreateCommand {

    /**
     * Creates a event task based on the information the user entered
     * @param inputArray the tokenized input that the user entered
     * @return the event task
     */
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

    /**
     * Creates a deadline task based on the information the user entered
     * @param inputArray the tokenized input that the user entered
     * @return the deadline task created
     */
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

    /**
     * Creates a to do task based on the information the user entered
     * @param inputArray the tokenized input that the user entered
     * @return the to do task created
     */
    public static ToDoTask createToDo(String[] inputArray) {
        String description = "";
        for (int i = 1; i < inputArray.length; ++i) {
            description = description + " " + inputArray[i];
        }
        description = description.trim();
        return new ToDoTask(description);
    }

    /**
     * handles the user input and decides which type of task to create
     * @param input user input
     * @return the response string upon successfully creating and adding the task into the task list
     */
    public static String runCommand(String input) {
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
