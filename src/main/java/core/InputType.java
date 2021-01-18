package core;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum InputType {
    LIST(x -> x.toLowerCase().startsWith("list"), (tm, data) -> {
        String ret = "";
        for(var x : tm.retrieveAllTasks()) {
            ret += x;
        }
        return ret;
    }),

    DONE(x -> x.toLowerCase().startsWith("done"), (tm, data) -> {
        int loc = Integer.parseInt(data.split(" ")[1]) - 1;
        tm.doTaskByListID(loc);
        return "The task has been set to done ! \n - " + tm.retrieveTaskByListID(loc);
    }),

    ADD(x -> true, (tm, data) -> {
        tm.addTask(new Task(data));
        return "added: " + data;
    }),

    UNKNOWN(x -> true, (tm, data) -> data);

    // ==========================================================================
    // ==================== CLASS DETAILS STARTING HERE =========================
    // ==========================================================================

    private Predicate<String> toMatch;
    private BiFunction<TaskManager, String, String> func;
    InputType(Predicate<String> toMatch, BiFunction<TaskManager, String, String> func) {
        this.toMatch = toMatch;
        this.func = func;
    }

    public boolean doesMatch(String x) {
        if(this.toMatch == null || x == null)
            return false;

        return toMatch.test(x);
    }

    public String apply(TaskManager tm, String data) {
        return func.apply(tm, data);
    }
}
