package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> collection;

    public TaskList() {
        this.collection = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> collection) {
        this.collection = collection;
    }

    public ArrayList<Task> getList() {
        return this.collection;
    }

    public String showList() {
        StringBuilder sb = new StringBuilder();

        sb.append("You got a total of " + this.collection.size() + " task(s).\n");
        for (int i = 0; i < collection.size(); i++) {
           sb.append(String.format("\t%d. %s\n", i + 1, collection.get(i)));
        }

        return sb.toString();
    }

    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();

        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < collection.size(); i++) {
            Task task = this.collection.get(i);
            if (task.getDescription().contains(keyword)) {
                sb.append(String.format("\t%d. %s\n", i + 1, collection.get(i)));
            }
        }
        return sb.toString();
    }

    public String addTask(String type, String[] args) throws DukeException {
        // Ensure task description and argument cannot be empty
        if (args[0].equals("")) {
            throw new DukeException("I need a description of your task...");
        } else if (args[1].equals("")) {
            if (type.equals("deadline"))
                throw new DukeException("I need to know when your task ends...");
            if (type.equals("event"))
                throw new DukeException("I need to know the time period of your event...");
        }

        // Add to collection
        try {
            switch (type) {
                case "todo":
                    this.collection.add(new Todo(args[0]));
                    break;
                case "deadline":
                    this.collection.add(new Deadline(args[0], LocalDate.parse(args[1])));
                    break;
                case "event":
                    this.collection.add(new Event(args[0], LocalDate.parse(args[1])));
                    break;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify a proper date... (Format: YYYY-MM-DD)");
        }
        return "Got it, I have added the task '" + args[0] + "' to your collection.";
    }

    public String markDone(String index) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(index.split(" ")[0]) - 1;
            boolean status = this.collection.get(itemIdx).markAsDone();
            if (!status)
                throw new IllegalArgumentException();
            return "Task '" + this.collection.get(itemIdx).getDescription() + "' is marked as done.";
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Task had already been marked as done...");
        }
    }

    public String deleteTask(String index) throws DukeException {
        try {
            int itemIdx = Integer.parseInt(index.split(" ")[0]) - 1;
            Task task = this.collection.remove(itemIdx);
            return "Task '" + task.getDescription() + "' has been deleted.";
        } catch (NumberFormatException e) {
            throw new DukeException("I need a task number...");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I don't think there is such a task...");
        }
    }
}
