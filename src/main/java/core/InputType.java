package core;

import core.task.*;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum InputType {


    DEADLINE(x -> x.toLowerCase().startsWith("deadline"), (tm, data) -> {
        Task t = new Deadline(data.substring(9));
        tm.addTask(t);
        return "Added the Deadline : " + t;
    }),

    DONE(x -> x.toLowerCase().startsWith("done"), (tm, data) -> {
        try {
            int loc = Integer.parseInt(data.substring(5)) - 1;
            tm.doTaskByListID(loc);
            return "The task has been set to done ! \n - " + tm.retrieveTaskByListID(loc);
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
            Task x = tm.retrieveTaskByListID(loc);
            tm.deleteTaskByListID(loc);
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

    public boolean doesMatch(String x) {
        if (this.toMatch == null || x == null)
            return false;

        return toMatch.test(x);
    }

    public String apply(TaskManager tm, String data) {
        return func.apply(tm, data);
    }
}
