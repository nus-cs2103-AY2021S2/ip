package duke.util;

import java.util.LinkedList;

import duke.task.Task;

import javafx.util.Pair;

/**
 * Class that serves as a Cache for Add, Delete, Done operations.
 */
public class Cache {
    private static final LinkedList<Pair<Pair<Task, Integer>, String>> stack = new LinkedList<>();

    /**
     * Stores command into the cache.
     *
     * @param task The task relevant to the command.
     * @param taskIdx The index of the task relevant to the command.
     * @param command The command to be stored.
     */
    public static void cache(Task task, int taskIdx, String command) {
        Pair<Task, Integer> taskIdxPair = new Pair<>(task, taskIdx);
        Pair<Pair<Task, Integer>, String> toCache = new Pair<>(taskIdxPair, command);
        stack.push(toCache);
    }

    /**
     * Retrieves the most recent command executed.
     *
     * @return Data regarding the most recent command.
     */
    public static Pair<Pair<Task, Integer>, String> pop() {
        return stack.pop();
    }

    /**
     * Checks if the cache is empty.
     *
     * @return True if the user has not executed any commands. False otherwise.
     */
    public static boolean isEmpty() {
        return stack.isEmpty();
    }
}
