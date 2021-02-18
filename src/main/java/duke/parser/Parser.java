package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandOption;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parses the user input and return corresponding Command objects for execution.
 * The naming convention for all functions with name prefixed with "prepare" in this file
 *       is adapted from the parser.java file from addressbook-level2. The link is:
 *      https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
 */
public class Parser {

    /**
     * Constructor.
     *
     */
    public Parser() {

    }

    /**
     * Returns the Task based on taskinfo stored in storage.
     *
     * @param taskInfo information stored in storage.
     * @return the Task.
     * @throws DukeException when information in storage is corrupted.
     */
    public static Task getTaskFromTaskInfo(String taskInfo) throws DukeException {
        String[] taskInfoArr = taskInfo.split("\\|");

        String type = taskInfoArr[0].strip();
        boolean isDone = taskInfoArr[1].strip().equals("1");
        String description = taskInfoArr[2].strip();

        Task task;
        switch(type) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "E":
            String at = taskInfoArr[3].strip();
            task = new Event(description, isDone, at);
            break;
        case "D":
            LocalDate by = parseByDate(taskInfoArr[3].strip());
            task = new Deadline(description, isDone, by);
            break;
        default:
            throw new DukeException("Invalid task info found in storage.");
        }

        return task;
    }

    /**
     * Parses the user input.
     * Takes in the first word from user input and carries out relevant actions based on
     *      the word by printing out corresponding replies.
     * Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first.
     *
     * @param userInput user input
     * @throws DukeException thrown if user enters a valid command but invalid related information
     *                       or an invalid command
     */
    public Command parseMessage(String userInput) throws DukeException {
        String[] processedUserInput = processUserInput(userInput);
        return getCommand(processedUserInput);
    }

    private String[] processUserInput(String userInput) {
        String[] msgArray = userInput.split(" ", 2);
        String commandWord = msgArray[0].strip();
        String otherInfo = null;
        boolean isDateProvided = msgArray.length > 1;
        if (isDateProvided) {
            otherInfo = msgArray[1].strip();
        }
        return new String[] {commandWord, otherInfo};
    }

    private Command getCommand(String[] processedUserInput) throws DukeException {
        String commandWord = processedUserInput[0];
        String otherInfo = processedUserInput[1];
        Command command;
        try {
            command = getCommandFromCommandWord(commandWord, otherInfo);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I do not understand this command.");
        }
        return command;
    }

    private Command getCommandFromCommandWord(String commandWord, String otherInfo) throws DukeException {
        CommandOption commandEnum = getCommandOption(commandWord);
        return createCommandFromCommandEnum(otherInfo, commandEnum);
    }

    /**
     * Creates the respective command based on the given information and commandEnum(command option).
     * This structure for this function is adapted from the parser.java file
     *      from addressbook-level2. The link is:
     *      https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
     *
     * @param otherInfo information relevant to the command.
     * @param commandEnum the command entered by user, an Enum.
     * @return the corresponding command.
     * @throws DukeException if the command given is invalid (eg. does not exist among all command options).
     */
    private Command createCommandFromCommandEnum(String otherInfo, CommandOption commandEnum) throws DukeException {
        Command command;
        switch (commandEnum) {
        case LIST:
            command = new ListCommand();
            break;
        case DONE:
            command = prepareCompleteTask(otherInfo);
            break;
        case TODO:
            command = prepareTodoTask(otherInfo);
            break;
        case EVENT:
            command = prepareEventTask(otherInfo);
            break;
        case DEADLINE:
            command = prepareDeadlineTask(otherInfo);
            break;
        case DELETE:
            command = prepareDeleteTask(otherInfo);
            break;
        case FIND:
            command = prepareFindTask(otherInfo);
            break;
        case UPDATE:
            command = prepareUpdateTask(otherInfo);
            break;
        default:
            throw new DukeException("Unexpected value: " + commandEnum);
        }
        return command;
    }

    private CommandOption getCommandOption(String commandWord) {
        return CommandOption.valueOf(commandWord.toUpperCase(Locale.ROOT));
    }

    private Command prepareCompleteTask(String taskIndex) throws DukeException {
        int index = parseTaskIndex(taskIndex);
        return new DoneCommand(index);
    }

    private Command prepareDeleteTask(String taskIndex) throws DukeException {
        int index = parseTaskIndex(taskIndex);
        return new DeleteCommand(index);
    }

    private Command prepareTodoTask(String description) throws DukeException {
        if (description == null) {
            throw new DukeException("Please provide a description when creating todo.");
        }
        return new AddCommand(new Todo(description));
    }

    private Command prepareDeadlineTask(String otherInfo) throws DukeException {
        String[] validatedInfo = getValidatedOtherInfo(otherInfo, "/by");
        String description = validatedInfo[0];
        LocalDate byDate = parseByDate(validatedInfo[1]);
        Task deadline = new Deadline(description, byDate);
        return new AddCommand(deadline);
    }

    private Command prepareEventTask(String otherInfo) throws DukeException {
        String[] validatedInfo = getValidatedOtherInfo(otherInfo, "/at");
        String description = validatedInfo[0];
        String at = validatedInfo[1];
        Task event = new Event(description, at);
        return new AddCommand(event);
    }

    private Command prepareFindTask(String keyword) throws DukeException {
        if (keyword == null || keyword.equals(" ") || keyword.equals("")) {
            throw new DukeException("Please enter a keyword when finding the task.");
        }
        return new FindCommand(keyword);
    }

    private Command prepareUpdateTask(String otherInfo) throws DukeException {
        String[] validatedInfo = getValidatedOtherInfoForUpdate(otherInfo);
        int taskIndexToUpdate = parseTaskIndex(validatedInfo[0]);
        LocalDate byDate = parseByDate(validatedInfo[1]);
        return new UpdateCommand(taskIndexToUpdate, byDate);
    }

    private String[] getValidatedOtherInfoForUpdate(String otherInfo) throws DukeException {
        String[] validatedOtherInfo;
        String splitBy = " ";
        try {
            validatedOtherInfo = prepareValidatedOtherInfo(otherInfo, splitBy);
            return validatedOtherInfo;
        } catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information "
                    + "when updating a task.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide the required information or "
                    + "ensure there is a whitespace between the task index and new date.");
        }
    }

    private String[] getValidatedOtherInfo(String otherInfo, String splitBy) throws DukeException {
        String[] validatedOtherInfo;
        try {
            validatedOtherInfo = prepareValidatedOtherInfo(otherInfo, splitBy);
            return validatedOtherInfo;
        } catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information "
                    + "when creating a task.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pleasure provide the required information or ensure "
                    + "/by is used when creating a deadline and "
                    + "/at is used when creating an event.");
        }
    }

    private String[] prepareValidatedOtherInfo(String otherInfo, String splitBy) throws DukeException {
        String[] splitOtherInfo = otherInfo.split(splitBy, 2);
        String description = splitOtherInfo[0].strip();
        String date = splitOtherInfo[1].strip();

        boolean noDescriptionOrDate = description.equals("") || date.equals("");
        if (noDescriptionOrDate) {
            throw new DukeException("Please provide the required information.");
        } else {
            return new String[] {description, date};
        }
    }

    private int parseTaskIndex(String taskIndex) throws DukeException {
        int unvalidatedIndex;
        if (taskIndex == null) {
            throw new DukeException("Please enter a task index.");
        }

        try {
            unvalidatedIndex = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Task index entered is not an integer.");
        }
        return unvalidatedIndex;
    }


    /**
     * Parses the given by date for deadline.
     *
     * @param dateInString by date in String.
     * @return a parsed local date object.
     * @throws DukeException when given date is invalid.
     */
    private static LocalDate parseByDate(String dateInString) throws DukeException {
        LocalDate date;
        try {
            date = LocalDate.parse(dateInString);
            assert !dateInString.isEmpty() : "deadline cannot be created without a by date!";
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid deadline date given. "
                    + "Please ensure date given is valid and follows the yyyy-mm-dd format, e.g. 2021-02-17.");
        }
        return date;
    }
}
