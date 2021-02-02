package ip.src.main.java;

/**
 * Ui class deals with user interactions.
 *
 */

public class Ui {
    protected Duke bot;

    Ui(Duke bot) {
        this.bot = bot;
    }

    /**
     * Deals with the done command by the user by telling the bot to mark a specified task as done.
     *
     * @param id The specified position of the task which status is to be updated as done in the Task List.
     */

    public void doneCommand(int id) {
        this.bot.markTaskAsDone(id);
    }

    /**
     * Deals with the ToDo command. Creates a ToDo task from the input and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the ToDo object.
     */

    public void toDoCommand(String content) {
        ToDo newTask = new ToDo(content);
        this.bot.addToList(newTask);
    }

    /**
     * Deals with the Event command. Creates a Event task from the input and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the Event object.
     * @param at Date of the Event object.
     */

    public void eventCommand(String content, String at) {
        Event newTask = new Event(content, at);
        this.bot.addToList(newTask);

    }

    /**
     * Deals with the Deadline command. Creates a Deadline task from the input and tells the bot to add the new task to its Task list.
     *
     * @param content Content of the Event object.
     * @param by Date of the Event object.
     */

    public void deadlineCommand(String content, String by) {
        Deadline newTask = new Deadline(content, by);
        this.bot.addToList(newTask);
    }

    /**
     * Deals with the Delete command. Tells the bot to remove a task from its Task list.
     *
     * @param id The specified position of the task to be removed from the bot Task List.
     */

    public void deleteCommand(int id){
        this.bot.deleteTask(id);
    }








}
