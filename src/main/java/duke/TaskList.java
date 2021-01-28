package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int id) {
        return tasks.get(id - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        if(tasks.isEmpty()) {
            return 0;
        } else {
            return tasks.size();
        }
    }

    public void addTask(Task ts) {
        tasks.add(ts);
    }

    public Task removeTask(int id) {
        return tasks.remove(id-1);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        if(this.getSize()==0) {
            message.append("There is no task in the list.\n");
        } else {
            for (int i = 1; i <= this.getSize(); i++) {
                message.append(i + ". " + this.getTask(i - 1) + "\n");
            }
        }
        return message.toString();
    }


}
