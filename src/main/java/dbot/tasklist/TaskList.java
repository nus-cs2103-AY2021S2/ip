package dbot.tasklist;

import dbot.task.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        this(100);
    }

    public TaskList(int initialSize) {
        super(initialSize);
    }

    public String encode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this) {
            String taskName = task.getClass().getSimpleName().toLowerCase();
            boolean isDone = task.getDone();
            String fullDescription = task.getFullDescription();
            stringBuilder.append(taskName + "|" + isDone + "|" + fullDescription + "\n");
        }
        return stringBuilder.toString();
    }
}
