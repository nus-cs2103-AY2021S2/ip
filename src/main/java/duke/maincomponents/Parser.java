package duke.maincomponents;

import java.util.ArrayList;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ShowListCommand;
import duke.command.ToDoCommand;
import duke.exceptions.DukeException;


public class Parser {

    private static boolean checkStringStartingEquals(String userInput, String stringToCheck) {
        return userInput.length() >= stringToCheck.length()
                && userInput.substring(0, stringToCheck.length()).equals(stringToCheck);
    }

    private static boolean checkStringEquals(String userInput, String stringToCheck) {
        return userInput.equals(stringToCheck) || userInput.equals(stringToCheck + " ");
    }

    private boolean equalsToList(String userInput) {
        return checkStringEquals(userInput, "list");
    }

    private boolean equalsToDone(String userInput) {
        return checkStringStartingEquals(userInput, "done");
    }

    /**
     * Parse a command that starts with done
     * @param userInput User Input that has "done" as its start
     * @return index of task to mark as done
     * @throws DukeException an exception to indicate an error
     */
    private int parseDoneCommand(String userInput) throws DukeException {
        int length = "done".length();
        if (checkStringEquals(userInput, "done")) {
            throw new DukeException("Error! Please indicate the task "
                    + "which is done by its number on the list");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after done. "
                    + "[eg. done 1]");
        } else {
            try {
                int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                return taskInt;
            } catch (NumberFormatException e) {
                throw new DukeException("Error! You must give a number corresponding "
                        + "to a task on the list to check as done");
            }
        }
    }

    private boolean equalsToDelete(String userInput) {
        return checkStringStartingEquals(userInput, "delete");
    }

    /**
     * Parse a command that starts with delete
     * @param userInput User input that has "delete" at its start
     * @return index of task to delete
     * @throws DukeException an exception to indicate an error
     */
    private int parseDeleteCommand(String userInput) throws DukeException {
        int length = "delete".length();
        if (checkStringEquals(userInput, "delete")) {
            throw new DukeException("Error! Please indicate the task which want to delete by its number on the list");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after delete. "
                    + "[eg. delete 1]");
        } else {
            try {
                int taskInt = Integer.parseInt(userInput.split(" ")[1]);
                return taskInt;
            } catch (NumberFormatException e) {
                throw new DukeException("Error! You must give a number corresponding to a task on the list to delete");
            }
        }
    }

    private boolean equalsToToDo(String userInput) {
        return checkStringStartingEquals(userInput, "todo");
    }

    /**
     * Parse a command that starts with "todo"
     * @param userInput User input with todo at its start
     * @return description of todo task to add
     * @throws DukeException an exception to indicate an error
     */
    private String parseToDoCommand(String userInput) throws DukeException {
        int length = "todo".length();
        if (checkStringEquals(userInput, "todo")) {
            throw new DukeException("Error! The description of a todo cannot be empty!");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after todo. [eg. todo sleep]");
        } else {
            return userInput.substring(length + 1);
        }
    }

    private boolean equalsToEvent(String userInput) {
        return checkStringStartingEquals(userInput, "event");
    }

    /**
     * Parse a command that has with event
     * @param userInput User input with event at it sstart
     * @return description of event and description of where the event is at in an arraylist
     * @throws DukeException an exception to indicate an error
     */
    private ArrayList<String> parseEventCommand(String userInput) throws DukeException {
        int length = "event".length();
        if (checkStringEquals(userInput, "event")) {
            throw new DukeException("Error! The description of a event cannot be empty!");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after event. [eg. event read /at 9pm]");
        } else {
            if (userInput.contains(" /at ")) {
                String[] arr = userInput.substring(length + 1).split(" /at ");
                String eventDescription = arr[0];
                String descriptionAt = arr[1];

                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(eventDescription);
                tempArr.add(descriptionAt);

                return tempArr;
            } else {
                throw new DukeException("Error! Please provide where and when the"
                        + " event will take place after /at. [eg event read /at 9pm]");
            }
        }
    }

    private boolean equalsToDeadline(String userInput) {
        return checkStringStartingEquals(userInput, "deadline");
    }
    private ArrayList<String> parseDeadlineCommand(String userInput) throws DukeException {
        int length = "deadline".length();
        if (checkStringEquals(userInput, "deadline")) {
            throw new DukeException("Error! The description of a deadline cannot be empty!");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after deadline. "
                    + "[eg. deadline submit work /by 2020-03-20]");
        } else {
            if (userInput.contains(" /by ")) {
                String[] arr = userInput.substring(length + 1).split(" /by ");
                String deadlineDescription = arr[0];
                String descriptionBy = arr[1];

                ArrayList<String> tempArr = new ArrayList<>();
                tempArr.add(deadlineDescription);
                tempArr.add(descriptionBy);

                return tempArr;
            } else {
                throw new DukeException("Error! Please provide when the deadline will be due after /by. "
                        + "[eg. deadline submit work /by 2020-03-20]");
            }
        }
    }

    private boolean equalsToFind(String userInput) {
        return checkStringStartingEquals(userInput, "find");
    }
    private String parseFindCommand(String userInput) throws DukeException {
        int length = "find".length();
        if (checkStringEquals(userInput, "find")) {
            throw new DukeException("Error! The description of what you wish to find cannot be empty!");
        } else if (!checkStringEquals(userInput.substring(length, length + 1), " ")) {
            throw new DukeException("Error! Please include a space after the string you wish to find.");
        } else {
            return userInput.substring(length + 1);
        }
    }

    public boolean checkIfExit(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * Parses user input and return a command that can be executed
     * @param userInput userInput to parse
     * @return Command that can be executed
     * @throws DukeException an execption
     */
    public Command parse(String userInput) throws DukeException {
        try {
            if (this.equalsToList(userInput)) {
                return new ShowListCommand();
            } else if (this.equalsToDone(userInput)) {
                int taskDoneInt = this.parseDoneCommand(userInput);
                return new DoneCommand(taskDoneInt);
            } else if (this.equalsToDelete(userInput)) {
                int taskDeleteInt = this.parseDeleteCommand(userInput);
                return new DeleteCommand(taskDeleteInt);
            } else if (this.equalsToToDo(userInput)) {
                String toDoDescription = this.parseToDoCommand(userInput);
                return new ToDoCommand(toDoDescription);
            } else if (this.equalsToEvent(userInput)) {
                ArrayList<String> eventDescription = this.parseEventCommand(userInput);
                return new EventCommand(eventDescription);
            } else if (this.equalsToDeadline(userInput)) {
                ArrayList<String> eventDescription = this.parseDeadlineCommand(userInput);
                return new DeadlineCommand(eventDescription);
            } else if (this.equalsToFind(userInput)) {
                String stringToFind = this.parseFindCommand(userInput);
                return new FindCommand(stringToFind);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means");
            }

        } catch (DukeException e) {
            throw e;
        }
    }
}
