package dukeproject;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gui.Ui;

/**
 * Represents a parser to make sense of string inputs.
 */
public class Parser {
    public enum CommandType {
        INPUT_DEADLINE,
        INPUT_EVENT,
        FILE_DEADLINE,
        FILE_EVENT
    }

    /**
     * Reads and understand the user inputs.
     *
     * @param userInput User input from the GUI.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     * @return A boolean on whether the application should continue running.
     */
    public boolean parseUserInput(String userInput, Ui ui, Storage storage, TaskList taskList) {
        if (userInput.equals("bye")) {
            // Exit when the user inputs "bye"
            ui.goodByeMessage();
            return false;
        } else if (userInput.equals("list")) {
            ui.printTaskList(taskList);
        } else if (userInput.startsWith("todo")) {
            // Create a new to do task base on command information
            parseToDoCommand(userInput, ui, storage, taskList);
        } else if (userInput.startsWith("deadline")) {
            // Create a new deadline task base on command information
            parseDeadlineCommand(userInput, ui, storage, taskList);
        } else if (userInput.startsWith("event")) {
            // Create a new event task base on command information
            parseEventCommand(userInput, ui, storage, taskList);
        } else if (userInput.startsWith("done")) {
            // Mark the event as done
            parseDoneCommand(userInput, ui, storage, taskList);
        } else if (userInput.startsWith("delete")) {
            // Delete the associated event
            parseDeleteCommand(userInput, ui, storage, taskList);
        } else if (userInput.startsWith("find")) {
            // Find a return a list of task that is related to the keyword
            parseForFind(userInput, ui, taskList);
        } else {
            // Unable to detect the user's input
            ui.printUnreadableError();
        }
        return true;
    }

    /**
     * Understands the to do command by the user.
     *
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     */
    public void parseToDoCommand(String command, Ui ui, Storage storage, TaskList taskList) {
        try {
            // Catch error where the user put empty spaces as description
            if (command.substring(5).isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }

            ToDo newToDoTask = new ToDo(command.substring(5));
            taskList.add(newToDoTask);
            storage.writeToFile(taskList);

            // Print a success message
            ui.printWithSpace(newToDoTask.successMessage(taskList.size()));
        } catch (StringIndexOutOfBoundsException ex) {
            // Description is empty
            ui.printDescriptionError();
        } catch (FileNotFoundException ex) {
            // File is empty
            ui.printFileError();
        }
    }

    /**
     * Understands the deadline command by the user.
     *
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     */
    public void parseDeadlineCommand(String command, Ui ui, Storage storage, TaskList taskList) {
        try {
            // Catch error where the user put empty spaces as description
            if (command.substring(9).isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }

            // Get the description and date from the user's input
            StringDatePair output = new Parser().parse(command, Parser.CommandType.INPUT_DEADLINE);

            // Add a deadline task
            Deadline newDeadlineTask = new Deadline(output.getString(), output.getDate());
            taskList.add(newDeadlineTask);
            storage.writeToFile(taskList);

            // Print a success message
            ui.printWithSpace(newDeadlineTask.successMessage(taskList.size()));
        } catch (StringIndexOutOfBoundsException ex) {
            // Description is empty
            ui.printDescriptionError();
        } catch (FileNotFoundException ex) {
            // File is empty
            ui.printFileError();
        } catch (DateTimeParseException ex) {
            // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
            ui.printDateFormatError();
        }
    }

    /**
     * Understands the event command by the user.
     *
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     */
    public void parseEventCommand(String command, Ui ui, Storage storage, TaskList taskList) {
        try {
            // Catch error where the user put empty spaces as description
            if (command.substring(5).isBlank()) {
                throw new StringIndexOutOfBoundsException();
            }

            // Get the description and date from the user's input
            StringDatePair output = new Parser().parse(command, Parser.CommandType.INPUT_EVENT);

            // Add a deadline task
            Event newEventTask = new Event(output.getString(), output.getDate());
            taskList.add(newEventTask);
            storage.writeToFile(taskList);

            // Print a success message
            ui.printWithSpace(newEventTask.successMessage(taskList.size()));
        } catch (StringIndexOutOfBoundsException ex) {
            // Description is empty
            ui.printDescriptionError();
        } catch (FileNotFoundException ex) {
            // File is empty
            ui.printFileError();
        } catch (DateTimeParseException ex) {
            // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
            ui.printDateFormatError();
        }
    }

    /**
     * Understands the done command by the user.
     *
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     */
    public void parseDoneCommand(String command, Ui ui, Storage storage, TaskList taskList) {
        try {
            // Mark the task as done based on the number given after the "done" input
            int taskIndex = Integer.parseInt(command.substring(5));

            // Mark the task as done
            taskList.get(taskIndex - 1).markAsDone();
            storage.writeToFile(taskList);

            // Print success message that the task was marked as done
            ui.printTaskSuccess(taskList, taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            // Task number is empty
            ui.printTaskNumError();
        } catch (FileNotFoundException ex) {
            // File is empty
            ui.printFileError();
        }
    }

    /**
     * Understands the delete command by the user.
     *
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     */
    public void parseDeleteCommand(String command, Ui ui, Storage storage, TaskList taskList) {
        try {
            // Delete task
            int taskIndex = Integer.parseInt(command.substring(7)) - 1;
            Task taskToBeRemoved = taskList.get(taskIndex);

            // Remove the appropriate task away from the list of task
            taskList.remove(taskIndex);
            storage.writeToFile(taskList);
            ui.printWithSpace(taskToBeRemoved.deleteMessage(taskList.size()));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            // Task number is empty
            ui.printTaskNumError();
        } catch (FileNotFoundException ex) {
            // File is empty
            ui.printFileError();
        }
    }

    /**
     * Parse a find command.
     *
     * @param command Command / input to find.
     */
    public void parseForFind(String command, Ui ui, TaskList taskList) {
        try {
            String keyword = command.substring(5);
            ui.printKeywordTaskList(taskList, keyword);
        } catch (Exception ex) {
            ui.printFindError();
        }
    }

    /**
     * Returns an object which contains the description and date of a task.
     *
     * @param fullCommand The input that the parser will try to make sense of.
     * @param cmdType The command type which determines how we try to interpret the data.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair parse(String fullCommand, CommandType cmdType) {
        if (cmdType == CommandType.INPUT_DEADLINE) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(9).split("/by ");
            String description = userInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime by = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, by);
        } else if (cmdType == CommandType.INPUT_EVENT) {
            // Split up the string into the description and date accordingly
            String[] userInputValues = fullCommand.substring(6).split("/at ");
            String description = userInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime at = LocalDateTime.parse(userInputValues[1], dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, at);
        } else if (cmdType == CommandType.FILE_DEADLINE) {
            // Split up the string into the description and date accordingly
            String[] taskInputValues = fullCommand.substring(7).split(" \\(by: ");
            String description = taskInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
            LocalDateTime by = LocalDateTime.parse(
                taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
                dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, by);
        } else if (cmdType == CommandType.FILE_EVENT) {
            // Split up the string into the description and date accordingly
            String[] taskInputValues = fullCommand.substring(7).split(" \\(at: ");
            String description = taskInputValues[0];

            // Specific the date format that our system will accept and save it in the by variable
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
            LocalDateTime at = LocalDateTime.parse(
                taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
                dateFormatter);

            // Return the description and date pair
            return new StringDatePair(description, at);
        }
        return null;
    }
}
