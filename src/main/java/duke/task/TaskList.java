package duke.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        lst = new ArrayList<>();
    }

    public int size() {
        return this.lst.size();
    }

    public void add(Task t) {
        lst.add(t);
    }

    public Task get(int idx) {
        return lst.get(idx);
    }

    public Task remove(int idx) {
        return lst.remove(idx);
    }

    public void forEach(Consumer<? super Task> consumer) {
        lst.forEach(consumer);
    }

    public List<Task> find(String findStr) {
        String[] wordsToFind = findStr.split(" ");

        List<Task> result = new ArrayList<>();
        HashSet<Task> found = new HashSet<>();

        for (String target : wordsToFind) {
            for (Task t : lst) {
                if (found.contains(t)) {
                    continue;
                }

                if (t.hasStrInProps(target)) {
                    found.add(t);
                    result.add(t);
                }
            }

            if (found.size() == result.size()) {
                break;
            }
        }

        return result;
    }
}
