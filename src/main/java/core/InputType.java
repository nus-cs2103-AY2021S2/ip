package core;

import core.task.Deadline;
import core.task.Event;
import core.task.Task;
import core.task.TaskAlreadyDoneException;
import core.task.TaskManager;
import core.task.Todo;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Enumerates the input types.
 */
public enum InputType {
    FIND(x -> x.toLowerCase().trim().startsWith("find"), (tm, data) -> {
        StringBuilder ret = new StringBuilder("Matched items : \n");
        var data2 = data.substring(5);
        int cnt = 1;

        Predicate<Task> partialSearchFunction = task -> task.contains(data2.trim());

        for (var x : tm.retrieveAllTasks().stream().filter(partialSearchFunction).toArray()) {
            ret.append(cnt++).append(". ").append(x).append("\n");
        }
        return ret.toString();
    }),


    DEADLINE(x -> x.toLowerCase().startsWith("deadline"), (tm, data) -> {
        Task t = new Deadline(data.substring(9));
        tm.addTask(t);
        return "Added the Deadline : " + t;
    }),

    DONE(x -> x.toLowerCase().startsWith("done"), (tm, data) -> {
        try {
            int loc = Integer.parseInt(data.substring(5)) - 1;
            tm.doTaskByListId(loc);
            return "The task has been set to done ! \n - " + tm.retrieveTaskByListId(loc);
        } catch (NumberFormatException e1) {
            return "Not a valid index given !!";
        } catch (IndexOutOfBoundsException | TaskAlreadyDoneException e) {
            return e.getMessage();
        }
    }),

    EVENT(x -> x.toLowerCase().startsWith("event"), (tm, data) -> {
        Task t = new Event(data.substring(6));
        tm.addTask(t);
        return "Added the Event : " + t;
    }),

    LIST(x -> x.toLowerCase().startsWith("list"), (tm, data) -> {
        StringBuilder ret = new StringBuilder();
        int cnt = 1;

        for (var x : tm.retrieveAllTasks()) {
            ret.append(cnt++).append(". ").append(x).append("\n");
        }
        return ret.toString();
    }),

    TODO(x -> x.toLowerCase().startsWith("todo"), (tm, data) -> {
        try {
            Task t = new Todo(data.substring(5));
            tm.addTask(t);
            return "Added the TODO : " + t;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }),

    DELETE(x -> x.toLowerCase().startsWith("delete"), (tm, data) -> {
        try {
            int loc = Integer.parseInt(data.substring(5)) - 1;
            Task x = tm.retrieveTaskByListId(loc);
            tm.deleteTaskByListId(loc);
            return "The task has been deleted ! \n - " + x;
        } catch (NumberFormatException e1) {
            return "Not a valid index given !!";
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }),

    UNKNOWN(x -> true, (tm, data) -> "Unknown command!! I don't know what you mean");

    // ==========================================================================
    // ==================== CLASS DETAILS STARTING HERE =========================
    // ==========================================================================

    private final Predicate<String> toMatch;
    private final BiFunction<TaskManager, String, String> func;

    InputType(Predicate<String> toMatch, BiFunction<TaskManager, String, String> func) {
        this.toMatch = toMatch;
        this.func = func;
    }

    /**
     * Returns whether the given data is of this input type. Encapsulates input parsing.
     * @param x - the given string
     * @return - whether it is of this {@code InputType}
     */
    public boolean doesMatch(String x) {
        if (this.toMatch == null || x == null)
            return false;

        return toMatch.test(x);
    }

    /**
     * Encapsulates the action associated with the particular {@code InputType}.
     * @param tm - instance of {@code TaskManager}
     * @param data - data to act on
     * @return - the output of the action to be outputted.
     */
    public String apply(TaskManager tm, String data) {
        return func.apply(tm, data);
    }
}
