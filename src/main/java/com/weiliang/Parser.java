package com.weiliang;

import com.weiliang.task.Deadline;
import com.weiliang.task.Event;
import com.weiliang.task.Task;
import com.weiliang.task.TaskList;

/**
 * Handles command related operations.
 */
public class Parser {

    private final TaskList tasks;

    /**
     * Instantiates a new parser object for operating commands.
     *
     * @param tasks Task list to handle.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a reply containing list of tasks.
     *
     * @return Formatted reply.
     * @throws DukeException If unable to fetch from task list.
     */
    public String list() throws DukeException {
        String message = "Printing list!";
        for (int i = 0; i < tasks.size(); i++) {
            message += "\n" + (i + 1) + "." + tasks.get(i);
        }
        return message;
    }

    /**
     * Returns the bot's response after deleting task with given index.
     *
     * @param index The index of the task in the task list.
     * @return The bot's response.
     * @throws DukeException If the task cannot be removed.
     */
    public String delete(int index) throws DukeException {
        // Mark complete
        Task task = tasks.remove(index);

        String message = "Noted, I have removed this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns a bot's response after marking a done as complete.
     *
     * @param index The position of the task in the task list to mark.
     * @return The bot's response.
     * @throws DukeException If the task cannot be marked as complete.
     */
    public String markDone(int index) throws DukeException {
        // Mark complete
        Task task = tasks.get(index);
        task.markComplete();

        assert task.isComplete() : "Failed to mark done";

        String message = "Nice, I've marked the task as done!";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns the bot's response after creating a to-do task.
     *
     * @param description The description of the task.
     * @return The bot's response.
     */
    public String createToDo(String description) {
        Task task = new Task(description);
        tasks.add(task);

        assert tasks.contains(task) : "Failed to add task";

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns the bot's response after creating a deadline task.
     *
     * @param description The description of the task.
     * @param time The time of the task.
     * @return The bot's reply.
     * @throws DukeException If the deadline task can't be created.
     */
    public String createDeadline(String description, String time) throws DukeException {
        Task task = new Deadline(description, time);
        tasks.add(task);

        assert tasks.contains(task) : "Failed to add deadline";

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns the bot's response after creating an event task.
     *
     * @param description The description of the task.
     * @param time The time of the task.
     * @return The bot's reply.
     * @throws DukeException If the event task can't be created.
     */
    public String createEvent(String description, String time) throws DukeException {
        Task task = new Event(description, time);
        tasks.add(task);

        assert tasks.contains(task) : "Failed to add event";

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    /**
     * Returns the bot's response after searching through task list for matching task description.
     *
     * @param searchField The search string.
     * @return The bot's response.
     */
    public String search(String searchField) {
        String message = "Printing matches!";
        int i = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(searchField)) {
                message += "\n" + (i + 1) + "." + task;
            }
            i++;
        }
        if (i == 0) {
            message += "\nNo results found!";
        }
        return message;
    }

    /**
     * Returns the bot's reply after undoing the previous action.
     *
     * @return The bot's reply.
     * @throws DukeException If unable to undo.
     */
    public String undo() throws DukeException {
        tasks.undo();
        return "Successfully undone previous action!";
    }
}
