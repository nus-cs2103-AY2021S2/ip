package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}
