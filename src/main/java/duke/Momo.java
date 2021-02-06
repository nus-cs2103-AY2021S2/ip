package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.ui.TextUi;
import duke.task.Task;

public class Momo {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Creates a Momo with an original task list if file path exists.
     * Creates a Momo with empty task list else.
     *
     * @param filePath the path of the *.txt file including information of existing tasks.
     */
    public Momo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (ParseException | IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets response if input is "bye".
     *
     * @return text UI for BYE.
     */
    private String getResponseForBye() {
        return TextUi.showExitUi();
    }

    /**
     * Gets response if input is "list".
     *
     * @return text UI for LIST.
     */
    private String getResponseForList() {
        return TextUi.showList(this.tasks);
    }

    /**
     * Gets response if input is "done x", where x is an integer.
     *
     * @param restInput x in the String format.
     * @return text UI for DONE.
     */
    private String getResponseForDone(String restInput) {
        int i = Integer.parseInt(restInput);
        try {
            return TextUi.showSuccessfulMark(tasks.mark(i));
        } catch (IndexOutOfBoundsException e) {
            return TextUi.showIndexOutOfBoundsError(tasks);
        }
    }

    /**
     * Gets response if input is "delete x", where x in an integer.
     *
     * @param restInput x in the String format.
     * @return text UI for DONE.
     */
    private String getResponseForDelete(String restInput) {
        int j = Integer.parseInt(restInput);
        try {
            Task taskToBeDeleted = tasks.delete(j);
            storage.save(tasks);
            return TextUi.showSuccessfulDelete(tasks, taskToBeDeleted);
        } catch (IndexOutOfBoundsException e) {
            return TextUi.showIndexOutOfBoundsError(tasks);
        }
    }

    /**
     * Gets response if input is "find {keyword}", where {keyword} is a string.
     *
     * @param restInput the {keyword}.
     * @return textUI for FIND.
     */
    private String getResponseForFind(String restInput) {
        return TextUi.showMatchingResult(tasks.find(restInput));
    }

    /**
     * Gets response if input is "todo/deadline/event {description}", where {description} is a string.
     *
     * @param command todo or deadline or event
     * @param restInput the {description}.
     * @return textUI for TODO / DEADLINE / EVENT.
     */
    private String getResponseForAdd(Command command, String restInput) {
        try {
            Task taskToBeAdded = Parser.parseDescription(command, restInput);
            tasks.add(taskToBeAdded);
            storage.save(tasks);
            return TextUi.showSuccessfulAdd(tasks, taskToBeAdded);
        } catch (ParseException e) {
            return TextUi.formatInChatBox(e.getMsgDes());
        } catch (DateTimeParseException e) {
            return TextUi.showDateParseError();
        }
    }

    /**
     * Gets response if input is not valid.
     *
     * @return textUI for invalid input.
     */
    private String getResponseForInvalidResponse() {
        return TextUi.showGeneralError();
    }

    /**
     * Throws StringIndexOutOfBoundsException if input is a single word.
     *
     * @param input user input.
     */
    private void testIsInputMultipleWords(String input) {
        int endOfFirstWord = input.indexOf(' ');
        if (endOfFirstWord < 0 || endOfFirstWord >= input.length()) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /**
     * Gets command from user input of multiple words.
     *
     * @param input total user input.
     * @return valid command.
     */
    private Command getCommandForMultipleWords(String input) {
        return Parser.parseCommandForMultipleWords(input);
    }

    /**
     * Gets command from user input of single word.
     *
     * @param input total user input.
     * @return valid command.
     */
    private Command getCommandForSingleWord(String input) {
        return Parser.parseCommandForSingleWord(input);
    }

    /**
     * Gets the rest user input apart from command.
     *
     * @param input total user input.
     * @return the rest user input.
     */
    private String getRestInput(String input) {
        return Parser.parseRestInput(input);
    }

    /**
     * Gets the response for single word input.
     *
     * @param input total user input.
     * @return textUI for single word input.
     */
    private String getResponseForSingleWord(String input) {
        try {
            getCommandForSingleWord(input);
        } catch (IllegalArgumentException e1) {
            return getResponseForInvalidResponse();
        }
        Command command = getCommandForSingleWord(input);
        switch (command) {
        case BYE:
            return getResponseForBye();
        case LIST:
            return getResponseForList();
        default:
            assert false;
        }
        return getResponseForInvalidResponse();
    }

    /**
     * Gets responses for multiple words input.
     *
     * @param input total user input.
     * @return textUI for multiple words input.
     */
    private String getResponseForMultipleWords(String input) {
        Command command = getCommandForMultipleWords(input);
        String restInput = getRestInput(input);
        switch (command) {
        case DONE:
            return getResponseForDone(restInput);
        case DELETE:
            return getResponseForDelete(restInput);
        case FIND:
            return getResponseForFind(restInput);
        default:
            return getResponseForAdd(command, restInput);
        }
    }

    /**
     * Gets responses from Momo using the input of users.
     *
     * @param input input of user.
     * @return answer of Momo.
     */
    public String getResponse(String input) {
        try {
            testIsInputMultipleWords(input);
        } catch (StringIndexOutOfBoundsException e) {
            return getResponseForSingleWord(input);
        }
        try {
            return getResponseForMultipleWords(input);
        } catch (IllegalArgumentException e) {
            return getResponseForInvalidResponse();
        }
    }
}
