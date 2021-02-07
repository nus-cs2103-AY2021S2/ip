package mike;

import mike.task.Task;
import mike.ui.TextUi;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Mike {

    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    /**
     * Create a Mike Object with a taskList from an existing file
     * Otherwise creates a Mike Object with an empty taskList
     *
     * @param filePath the path of the *.txt file holding information of existing tasks
     */
    public Mike(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new TextUi();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (ParseException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        } catch (IOException e) {
            ui.showIOError(e);
        }
    }

    /**
     * Gets response when "BYE" is the input
     *
     * @return text UI for BYE command
     */
    private String getResponseForByeCommand() {
        return ui.showExitUi();
    }

    /**
     * Gets response when "LIST" is the input
     *
     * @return text UI for LIST command
     */
    private String getResponseForListCommand() {
        return ui.showListUi(taskList);
    }

    /**
     * Gets response when "DONE x" is the input
     *
     * @param descriptionInput x as a String
     * @return text UI for DONE command
     */
    private String getResponseForDoneCommand(String descriptionInput) {
        try {
            int i = Integer.parseInt(descriptionInput);
            return ui.showMarkSuccess(taskList.mark(i));
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBoundsError(taskList);
        }
    }

    /**
     * Gets response when "DELETE x" is the input
     *
     * @param descriptionInput x as a String
     * @return text UI for DELETE command
     */
    private String getResponseForDeleteCommand(String descriptionInput) {
        try {
            int i = Integer.parseInt(descriptionInput);
            Task deletedTask = taskList.delete(i);
            return ui.showDeleteSuccess(taskList, deletedTask);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBoundsError(taskList);
        }
    }

    /**
     * Gets response when "FIND {keyword}" is the input
     *
     * @param descriptionInput the {keyword} as a String
     * @return text UI for FIND command
     */
    private String getResponseForFindCommand(String descriptionInput) {
        return ui.showMatchingResults(taskList.find(descriptionInput));
    }

    /**
     * Gets response when "TODO/DEADLINE/EVENT {description}" is the input
     *
     * @param command          TODO/DEADLINE/EVENT command
     * @param descriptionInput the {description} as a String
     * @return text UI for TODO/DEADLINE/EVENT/ command
     */
    private String getResponseForAddCommand(Command command, String descriptionInput) {
        try {
            Task addedTask = Parser.parseDescription(command, descriptionInput);
            taskList.add(addedTask);
            String response = ui.showAddSuccess(taskList, addedTask);
            storage.save(taskList);
            return response;
        } catch (ParseException e) {
            return ui.showError(e);
        } catch (IOException e) {
            return ui.showIOError(e);
        } catch (DateTimeParseException e) {
            return ui.showDateTimeParseError(e);
        }
    }

    /**
     * Gets a response if input is invalid
     *
     * @return text UI for invalid input
     */
    private String getResponseForInvalidInput() {
        return ui.showGeneralError();
    }

    /**
     * Gets command from a single-word user input
     *
     * @param input user input as a String
     * @return Command object
     */
    private Command getCommandForSingleWord(String input) {
        return Parser.parseCommandForSingleWord(input);
    }

    /**
     * Gets command from a multiple-word user input
     *
     * @param input user input as a String
     * @return Command object
     */
    private Command getCommandForMultipleWords(String input) {
        return Parser.parseCommandForMultipleWords(input);
    }

    /**
     * Gets the response from a single-word user input
     *
     * @param input user input as a String
     * @return text UI for a single-word user input
     */
    private String getResponseForSingleWord(String input) {
        try {
            Command command = getCommandForSingleWord(input);
            switch (command) {
            case BYE:
                return getResponseForByeCommand();
            case LIST:
                return getResponseForListCommand();
            default:
                assert false;
                return getResponseForInvalidInput();
            }
        } catch (IllegalArgumentException e) {
            return getResponseForInvalidInput();
        }
    }

    /**
     * Gets response for a multiple-words user input
     *
     * @param input user input as a String
     * @return text UI for a multipler-words user input
     */
    private String getResponseForMultipleWords(String input) {
        Command command = getCommandForMultipleWords(input);
        String descriptionInput = getDescriptionInput(input);
        switch (command) {
        case DONE:
            return getResponseForDoneCommand(descriptionInput);
        case DELETE:
            return getResponseForDeleteCommand(descriptionInput);
        case FIND:
            return getResponseForFindCommand(descriptionInput);
        default:
            return getResponseForAddCommand(command, descriptionInput);
        }
    }

    /**
     * Gets the description portion of the user input
     *
     * @param input user input as a String
     * @return the description portion of the user input as a String
     */
    private String getDescriptionInput(String input) {
        return Parser.parseDescriptionInput(input);
    }

    /**
     * Checks if input has multiple words
     * throws a StringIndexOutOfBoundsException if input is a single word
     *
     * @param input user input as a String
     */
    private void checkIfInputHasMultipleWords(String input) {
        int endIndexOfCommand = input.indexOf(' ');
        if (endIndexOfCommand < 0 || endIndexOfCommand >= input.length()) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    public String getResponse(String input) {
        try {
            checkIfInputHasMultipleWords(input);
        } catch (StringIndexOutOfBoundsException e) {
            return getResponseForSingleWord(input);
        }
        try {
            return getResponseForMultipleWords(input);
        } catch (IllegalArgumentException e) {
            return getResponseForInvalidInput();
        }
    }

    public static void main(String[] args) {
        Mike mike = new Mike("data/tasks.txt");
    }
}