import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import exception.DukeInvalidArgumentsException;

public class TaskList {

    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Event task command executor
     * @param input tokenized hashmap of inputs
     * @return Output string of the command
     * @throws DukeInvalidArgumentsException when the input has invalid arguments
     */
    protected String executeEvent(HashMap<String, String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("event", "The description of an event cannot be empty");
        }
        if (!input.containsKey("at")) {
            throw new DukeInvalidArgumentsException("event", "The date for an event cannot be empty");
        }
        try {
            LocalDateTime.parse(input.get("at"), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            throw new DukeInvalidArgumentsException("event",
                    "The date argument is in an invalid format. Date format should be d/M/yyyy HHmm");
        }
        return addTaskAndReturnMessage(new EventTask(input.get("info"), input.get("at")));
    }

    /**
     * Deadline task command executor
     * @param input tokenized hashmap of inputs
     * @return Output string of the command
     * @throws DukeInvalidArgumentsException when the input has invalid arguments
     */
    protected String executeDeadline(HashMap<String, String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("deadline", "The description of a deadline cannot be empty");
        }
        if (!input.containsKey("by")) {
            throw new DukeInvalidArgumentsException("deadline", "The date for a deadline cannot be empty");
        }
        try {
            LocalDateTime.parse(input.get("by"), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            throw new DukeInvalidArgumentsException("deadline",
                    "The date argument is in an invalid format. Date format should be d/M/yyyy HHmm");
        }
        return addTaskAndReturnMessage(new DeadlineTask(input.get("info"), input.get("by")));
    }

    /**
     * Todo task command executor
     * @param input tokenized hashmap of inputs
     * @return Output string of the command
     * @throws DukeInvalidArgumentsException when the input has invalid arguments
     */
    protected String executeTodo(HashMap<String, String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("todo", "The description of a todo cannot be empty");
        }
        return addTaskAndReturnMessage(new TodoTask(input.get("info")));
    }

    /**
     * List command executor
     * @return Output string of the command
     */
    protected String executeList() {
        String output = "";

        for (int i = 0; i < storage.tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, storage.tasks.get(i));
        }

        if (output.isEmpty()) {
            return "You have no tasks.";
        }
        return output.substring(0, output.length() - 1);
    }

    /**
     * Delete command executor
     * @param tokenizedInput tokenized hashmap of inputs
     * @return Output string of the command
     * @throws DukeInvalidArgumentsException when the input has invalid arguments
     */
    protected String executeDelete(HashMap<String, String> tokenizedInput) throws DukeInvalidArgumentsException {
        int index = -1;
        try {
            index = Integer.parseInt(tokenizedInput.get("info"));
        } catch (Exception e) {
            throw new DukeInvalidArgumentsException("delete", "input index is not an integer");
        }
        if (index < 0 || index > storage.tasks.size()) {
            throw new DukeInvalidArgumentsException("delete", "input index is not a valid index");
        }

        Task t = storage.tasks.remove(index - 1);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                t.toString(), storage.tasks.size());
    }

    /**
     * Done command executor
     * @param tokenizedInput tokenized hashmap of inputs
     * @return Output string of the command
     * @throws DukeInvalidArgumentsException when the input has invalid arguments
     */
    protected String executeDone(HashMap<String, String> tokenizedInput) throws DukeInvalidArgumentsException {
        int index = -1;
        try {
            index = Integer.parseInt(tokenizedInput.get("info"));
        } catch (Exception e) {
            throw new DukeInvalidArgumentsException("done", "input index is not an integer");
        }
        if (index < 0 || index > storage.tasks.size()) {
            throw new DukeInvalidArgumentsException("done", "input index is not a valid index");
        }
        // TODO: Add Exception for out of range done
        Task t = storage.tasks.get(Integer.parseInt(tokenizedInput.get("info")) - 1);
        t.setTaskAsDone();
        return "Nice! I've marked this task as done:\n  " + t.toString();
    }

    /**
     * Find command executor
     * @param tokenizedInput tokenized hashmap of inputs
     * @return Output string of the command
     */
    protected String executeFind(HashMap<String, String> tokenizedInput) {
        String searchString = tokenizedInput.get("info");
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < storage.tasks.size(); i++) {
            if (storage.tasks.get(i).getTaskInfo().contains(searchString)) {
                output += String.format("%d.%s\n", i + 1, storage.tasks.get(i));
            }
        }
        return output.substring(0, output.length() - 1);
    }

    /**
     * Internal function used for formatting output string and adding task to storage.
     * @param task Task to be added
     * @return The output string formatted for the task
     */
    protected String addTaskAndReturnMessage(Task task) {
        storage.tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                storage.tasks.size());
    }

    /**
     * Archive task command executor
     * @param tokenizedInput tokenized hashmap of inputs
     * @return Output string of the command
     */
    public String executeArchive(HashMap<String, String> tokenizedInput) {
        Task t = storage.tasks.remove(Integer.parseInt(tokenizedInput.get("info")) - 1);
        t.setArchived(true);
        try {
            storage.archiveTask(t);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("Got it. I've archived this task:\n  %s\nNow you have %d tasks in the list.", t.toString(),
                storage.tasks.size());
    }
}
