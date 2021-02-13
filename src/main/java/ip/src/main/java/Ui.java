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

    public String toDoCommand(String content) {
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

    public String eventCommand(String content, String at) {
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

    public String deadlineCommand(String content, String by) {
        Deadline newTask = new Deadline(content, by);
        return this.bot.addToList(newTask);
    }

    /**
     * Deals with the Delete command. Tells the bot to remove a task from its Task list.
     *
     * @param id The specified position of the task to be removed from the bot Task List.
     */

    public String deleteCommand(int id) {
        return this.bot.deleteTask(id);
    }

    public String handleUserInput(String input) {
        Parser parser = new Parser();
        String output = "";
        try {
            Command command = parser.getCommand(input);

            switch (command) {
                case HELLO:
                    output = this.bot.greet();
                    break;

                case BYE:
                    output = "Bye!";
                    Storage storage = new Storage("data/duke.txt" , this.bot);
                    try {
                        storage.updateFile();
                    } catch (IOException e) {
                        output = "Can't save to file!";
                    }
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
                    try {
                        input = parser.toDoTask(input);
                        output = toDoCommand(input);
                        break;

                    } catch (Exception e) {
                        throw new DukeException(("OOPS!!! The description cannot be empty."));
                    }

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

