package ip.src.main.java;

import java.io.IOException;

/**
 * Ui class deals with user interactions.
 *
 */

public class Ui {
    protected Duke bot;

    public Ui(Duke bot) {
        this.bot = bot;
    }

    /**
     * Deals with the done command by the user by telling the bot to mark a specified task as done.
     *
     * @param id The specified position of the task which status is to be updated as done in the Task List.
     */
    public String doneCommand(int id) {
        return this.bot.markTaskAsDone(id);
    }

    /**
     * Deals with the ToDo command. Creates a ToDo task from the input
     * and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the ToDo object.
     */
    private String toDoCommand(String content) {
        ToDo newTask = new ToDo(content);
        return this.bot.addToList(newTask);
    }

    /**
     * Deals with the Event command.
     * Creates a Event task from the input and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the Event object.
     * @param at Date of the Event object.
     */
    private String eventCommand(String content, String at) {
        Event newTask = new Event(content, at);
        return this.bot.addToList(newTask);

    }

    /**
     * Deals with the Deadline command.
     * Creates a Deadline task from the input and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the Event object.
     * @param by Date of the Event object.
     */
    private String deadlineCommand(String content, String by) {
        Deadline newTask = new Deadline(content, by);
        return this.bot.addToList(newTask);
    }

    private String editCommand(String editDetails , int id , EditType editType) throws DukeException {
        try {
            Task oldTask = this.bot.getTask(id-1);
            Task newTask = new Task("");

            switch (editType) {
                case EDIT_DATETIME:
                    newTask = oldTask.editDate(editDetails);
                    break;

                case EDIT_CONTENT:
                    newTask = oldTask.editContent(editDetails);
                    break;

                default:
                    assert false : editType;
            }
            return this.bot.editTask(newTask , id);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! \n Please enter a valid task number!");
        }
    }

    /**
     * Deals with the Delete command. Tells the bot to remove a task from its Task list.
     *
     * @param id The specified position of the task to be removed from the bot Task List.
     */
    public String deleteCommand(int id) throws DukeException {
        try {
            return this.bot.deleteTask(id-1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! \n Please enter a valid task number!");
        }
    }

    /**
     * Handles user input for the different commands given by the user.
     * Gets the result string to respond to the user.
     *
     * @param input User input.
     * @return Result string.
     */
    public String handleUserInput(String input) {
        Parser parser = new Parser();
        String output = "";
        try {
            Command command = parser.getCommand(input);
            switch (command) {

                case BYE:
                    Storage storage = new Storage("data/duke.txt" , this.bot);
                    output = this.bot.bye(storage);
                    break;

                case LIST:
                    output = this.bot.printList();
                    break;

                case DONE:
                    int id = parser.getId(input);
                    output = doneCommand(id);
                    break;

                case FIND:
                    String keyword = parser.getKeyword(input);
                    output = this.bot.findMatchingTasks(keyword);
                    break;

                case TODO:
                    input = parser.toDoTask(input);
                    output = toDoCommand(input);
                    break;

                case EVENT:
                    String content = parser.eventTaskContent(input);
                    String at = parser.eventTaskAt(input);
                    output = eventCommand(content, at);
                    break;

                case DEADLINE:
                    content = parser.deadlineTaskContent(input);
                    String by = parser.deadlineTaskBy(input);
                    output = deadlineCommand(content, by);
                    break;

                case EDIT:
                    id = parser.getId(input);
                    EditType editType = parser.getEditType(input);
                    String editDetails = parser.getEditDetails(input);
                    output = editCommand(editDetails , id , editType);
                    break;

                case DELETE:
                    id = parser.getId(input);
                    output = deleteCommand(id);
                    break;

                default:
                    assert false : command;
            }

        } catch (DukeException e1) {
            output = e1.getMessage();
        }
        return output;
    }

}

