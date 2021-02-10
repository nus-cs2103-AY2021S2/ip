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

    private final int OFFSET_AFTER_CONTACTS_CMD = 8;

    /**
     * Reads and understand the user inputs.
     *
     * @param userInput User input from the GUI.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param taskList Represents the list of task.
     * @return A boolean on whether the application should continue running.
     */
    public boolean parseUserInput(String userInput, Ui ui, Storage storage, TaskList taskList, ContactList contacts) {
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
        } else if (userInput.startsWith("contact")) {
            // Only takes the string value after contact
            parseContactCommand(userInput, ui, storage, contacts);
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
            taskList.addTask(newToDoTask);
            storage.writeTasksToFile(taskList);

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
            StringDatePair output = new Parser().parseInputAndFileEvents(command, Parser.CommandType.INPUT_DEADLINE);

            // Add a deadline task
            Deadline newDeadlineTask = new Deadline(output.getString(), output.getDate());
            taskList.addTask(newDeadlineTask);
            storage.writeTasksToFile(taskList);

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
            StringDatePair output = new Parser().parseInputAndFileEvents(command, Parser.CommandType.INPUT_EVENT);

            // Add a deadline task
            Event newEventTask = new Event(output.getString(), output.getDate());
            taskList.addTask(newEventTask);
            storage.writeTasksToFile(taskList);

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
            taskList.getTask(taskIndex - 1).markAsDone();
            storage.writeTasksToFile(taskList);

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
            Task taskToBeRemoved = taskList.getTask(taskIndex);

            // Remove the appropriate task away from the list of task
            taskList.removeTask(taskIndex);
            storage.writeTasksToFile(taskList);
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
     * Parse a contact command.
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param contacts Represents the list of contacts.
     */
    public void parseContactCommand(String command, Ui ui, Storage storage, ContactList contacts) {
        try {
            if (command.startsWith("list", OFFSET_AFTER_CONTACTS_CMD)) {
                ui.printContactList(contacts);
            } else if (command.startsWith("add", OFFSET_AFTER_CONTACTS_CMD)) {
                parseContactAddCommand(command, ui, storage, contacts);
            } else if (command.startsWith("delete", OFFSET_AFTER_CONTACTS_CMD)) {
                parseContactDeleteCommand(command, ui, storage, contacts);
            } else {
                ui.printUnreadableError();
            }
        } catch (Exception ex) {
            ui.printGeneralParseError();
        }
    }

    /**
     * Parse an add contact command.
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param contacts Represents the list of contacts.
     */
    public void parseContactAddCommand(String command, Ui ui, Storage storage, ContactList contacts) {
        try {
            String[] inputs = command.substring(OFFSET_AFTER_CONTACTS_CMD + 4).split(" ");
            String fullName = inputs[0];
            String emailAddress = inputs[1];
            int contactNumber = Integer.parseInt(inputs[2]);

            contacts.addContact(fullName, emailAddress, contactNumber);
            ui.printContactAdded();
            storage.writeContactsToFile(contacts);
        } catch (Exception ex) {
            ui.printGeneralParseError();
        }
    }

    /**
     * Parse a delete contact command.
     * @param command Represents the command that the user has given.
     * @param ui Represents the UI object.
     * @param storage Represents the storage object.
     * @param contacts Represents the list of contacts.
     */
    public void parseContactDeleteCommand(String command, Ui ui, Storage storage, ContactList contacts) {
        try {
            int contactIndex = Integer.parseInt(command.substring(OFFSET_AFTER_CONTACTS_CMD + 7)) - 1;
            contacts.removeContact(contactIndex);
            ui.printContactDeleted();
            storage.writeContactsToFile(contacts);
        } catch (Exception ex) {
            ui.printTaskNumError();
        }
    }

    /**
     * Returns an object which contains the description and date of a task.
     *
     * @param fullCommand The input that the parser will try to make sense of.
     * @param cmdType The command type which determines how we try to interpret the data.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair parseInputAndFileEvents(String fullCommand, CommandType cmdType) {
        if (cmdType == CommandType.INPUT_DEADLINE) {
            return handleInputDeadlineCmd(fullCommand);
        } else if (cmdType == CommandType.INPUT_EVENT) {
            return handleInputEventCmd(fullCommand);
        } else if (cmdType == CommandType.FILE_DEADLINE) {
            return handleFileDeadlineCmd(fullCommand);
        } else if (cmdType == CommandType.FILE_EVENT) {
            return handleFileEventCmd(fullCommand);
        }
        return null;
    }

    /**
     * Handles and understand the input event command given by the user.
     * @param fullCommand Command given by the user.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair handleInputDeadlineCmd(String fullCommand) {
        String[] userInputValues = fullCommand.substring(9).split("/by ");
        String description = userInputValues[0];

        // Specific the date format that our system will accept and save it in the by variable
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(userInputValues[1], dateFormatter);

        return new StringDatePair(description, by);
    }

    /**
     * Handles and understand the input event command given by the user.
     * @param fullCommand Command given by the user.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair handleInputEventCmd(String fullCommand) {
        String[] userInputValues = fullCommand.substring(6).split("/at ");
        String description = userInputValues[0];

        // Specific the date format that our system will accept and save it in the by variable
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime at = LocalDateTime.parse(userInputValues[1], dateFormatter);

        return new StringDatePair(description, at);
    }

    /**
     * Handles and understand the file deadline command taken from the file.
     * @param fullCommand Command given by the user.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair handleFileDeadlineCmd(String fullCommand) {
        String[] taskInputValues = fullCommand.substring(7).split(" \\(by: ");
        String description = taskInputValues[0];

        // Specific the date format that our system will accept and save it in the by variable
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
        LocalDateTime by = LocalDateTime.parse(
            taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
            dateFormatter);

        return new StringDatePair(description, by);
    }

    /**
     * Handles and understand the file event command taken from the file.
     * @param fullCommand Command given by the user.
     * @return An object which contains the description and date of a task.
     */
    public StringDatePair handleFileEventCmd(String fullCommand) {
        String[] taskInputValues = fullCommand.substring(7).split(" \\(at: ");
        String description = taskInputValues[0];

        // Specific the date format that our system will accept and save it in the by variable
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy - HHmm");
        LocalDateTime at = LocalDateTime.parse(
            taskInputValues[1].substring(0, taskInputValues[1].length() - 1),
            dateFormatter);

        return new StringDatePair(description, at);
    }
}
