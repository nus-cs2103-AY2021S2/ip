package seashell;

import seashell.task.Deadline;
import seashell.task.Event;
import seashell.task.Task;
import seashell.task.Todo;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
