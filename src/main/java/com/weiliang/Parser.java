package com.weiliang;

import com.weiliang.task.Deadline;
import com.weiliang.task.Event;
import com.weiliang.task.Task;
import com.weiliang.task.TaskList;

/**
 * Handles command related operations.
 */
public class Parser {

    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public String list() throws DukeException {
        String message = "Printing list!";
        for (int i = 0; i < tasks.size(); i++) {
            message += "\n" + (i + 1) + "." + tasks.get(i);
        }
        return message;
    }

    public String delete(int index) throws DukeException {
        // Check if in tasks
        if (index > tasks.size() - 1) {
            throw new DukeException("Unable to remove item!");
        }

        // Mark complete
        Task task = tasks.remove(index);

        String message = "Noted, I have removed this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    public String markDone(int index) throws DukeException {
        // Check if in tasks
        if (index > tasks.size() - 1) {
            throw new DukeException("Unable to remove item!");
        }

        // Mark complete
        Task task = tasks.get(index);
        task.complete();

        String message = "Nice, I've marked the task as done!";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    public String createToDo(String description) throws DukeException {
        Task task = new Task(description);
        tasks.add(task);

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    public String createDeadline(String description, String time) throws DukeException {
        Task task = new Deadline(description, time);
        tasks.add(task);

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    public String createEvent(String description, String time) throws DukeException {
        Task task = new Event(description, time);
        tasks.add(task);

        String message = "Got it! I've added this task.";
        message += "\n" + task;
        message += "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        return message;
    }

    public String search(String searchField) {
        String message = "Printing matches!";
        int i = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(searchField)) {
                message += "\n" + (i+1) + "." + task;
            }
            i++;
        }
        if (i == 0) {
            message += "\nNo results found!";
        }
        return message;
    }
}
