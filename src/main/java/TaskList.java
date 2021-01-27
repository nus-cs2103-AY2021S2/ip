import java.util.HashMap;

import exception.*;
public class TaskList {

    Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public String executeEvent(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("event", "The description of an event cannot be empty");
        }
        if (!input.containsKey("at")) {
            throw new DukeInvalidArgumentsException("event", "The date for an event cannot be empty");
        }
        return addTaskAndReturnMessage(new EventTask(input.get("info"), input.get("at")));
    }

    public String executeDeadline(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("deadline", "The description of a deadline cannot be empty");
        }
        if (!input.containsKey("by")) {
            throw new DukeInvalidArgumentsException("deadline", "The date for a deadline cannot be empty");
        }
        return addTaskAndReturnMessage(new DeadlineTask(input.get("info"), input.get("by")));
    }

    public String executeTodo(HashMap<String,String> input) throws DukeInvalidArgumentsException {
        if (!input.containsKey("info")) {
            throw new DukeInvalidArgumentsException("todo", "The description of a todo cannot be empty");
        }
        return addTaskAndReturnMessage(new TodoTask(input.get("info")));
    }

    public String executeList() {
        String output = "";
        for (int i = 0; i < storage.tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, storage.tasks.get(i));
        }
        return output.substring(0, output.length() - 1);
    }

    public String executeDelete(HashMap<String,String> tokenizedInput) {
        // TODO: Add Exception for out of range deletion
        Task t = storage.tasks.remove(Integer.parseInt(tokenizedInput.get("info")) - 1);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d storage.tasks in the list.", t.toString(),
                storage.tasks.size());
    }

    public String executeDone(HashMap<String,String> tokenizedInput) {
        // TODO: Add Exception for out of range done
        Task t = storage.tasks.get(Integer.parseInt(tokenizedInput.get("info")) - 1);
        t.setTaskAsDone();
        return "Nice! I've marked this task as done:\n  " + t.toString();
    }

    public String addTaskAndReturnMessage(Task task) {
        storage.tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                storage.tasks.size());
    }
}
