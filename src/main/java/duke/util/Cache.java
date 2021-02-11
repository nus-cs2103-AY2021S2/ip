package duke.util;

import duke.task.Task;
import javafx.util.Pair;

import java.util.LinkedList;

public class Cache {
    private static final LinkedList<Pair<Pair<Task, Integer>, String>> stack = new LinkedList<>();

    public static void cache(Task task, int taskIdx, String command) {
        Pair<Task, Integer> taskIdxPair = new Pair<>(task, taskIdx);
        Pair<Pair<Task, Integer>, String> toCache = new Pair<>(taskIdxPair, command);
        stack.push(toCache);
    }

    public static Pair<Pair<Task, Integer>, String> pop() {
        return stack.pop();
    }

    public static boolean isEmpty() {
        return stack.isEmpty();
    }
}
