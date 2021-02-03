package duke;

import duke.command.*;
import duke.exception.DukeCommandException;
import duke.exception.DukeToDoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    // private final List<Task> taskList;

    // public Parser() {
    //     this.taskList = new ArrayList<>();
    // }
    //
    // public Parser(List<Task> taskList) {
    //     this.taskList = taskList;
    // }
    //
    // public List<Task> getTaskList() {
    //     return this.taskList;
    // }

    /**
     * Parses a userInput and outputs a command for execution
     * @param userInput
     * @return Different Command depending on input
     */

    public static Command parseCommand(String userInput) throws DukeToDoException, DukeCommandException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];

        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")){
            return parseDone(splitInput[1]);
        } else if (command.equals("delete")){
            return parseDelete(splitInput[1]);
        } else if (isValidTaskCommand(command)) {
            return parseTask(userInput);
        } else {
            throw new DukeCommandException("Sorry Human. I do not comprehend.\n"+
                    "Please read the user manual and try again");
        }

    }

    public static boolean isValidTaskCommand(String command) {
        return command.equals("event") || command.equals("deadline") || command.equals("todo");
    }

    /**
     * Parse the command args in the context of Done
     * @param arg
     * @return DoneCommand
     */
    public static Command parseDone(String arg) {
        int idxDone = Integer.parseInt(arg) - 1;
        return new DoneCommand(idxDone);
    }

    /**
     * Parse the command args in the context of Delete
     * @param arg
     * @return DeleteCommand
     */
    public static Command parseDelete(String arg) {
        int idxDel = Integer.parseInt(arg) - 1;
        return new DeleteCommand(idxDel);
    }

    /**
     * Parse the command in the context of Task
     * @param input
     * @return TaskCommand
     */
    public static Command parseTask(String input) throws DukeToDoException, DukeCommandException {

        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];

        switch (command) {
        case "todo":
            if(splitInput.length == 1) {
                throw new DukeToDoException("The description of a todo cannot be empty.");
            }
            String taskDescription = splitInput[1];
            return new TaskCommand(command, taskDescription);


        case "deadline":
            String[] taskDetails = splitInput[1].split("/by");
            taskDescription = taskDetails[0];
            LocalDate endTime =  LocalDate.parse(taskDetails[1]);
            return new TaskCommand(command, taskDescription, endTime);

        case "event":
            taskDetails = splitInput[1].split("/at");
            taskDescription = taskDetails[0];
            LocalDate eventTime =  LocalDate.parse(taskDetails[1]);
            return new TaskCommand(command, taskDescription, eventTime);

        default:
            throw new DukeCommandException("Invalid Command.");
        }

    }

}
