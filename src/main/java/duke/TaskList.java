package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Create and initialize a TaskList that handles the modifying of the list of tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getIndex(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void removeIndex(int i) {
        this.taskList.remove(i);
    }

    public void clear() {
        this.taskList.clear();
    }

    /**
     * Returns a TaskList of all tasks containing the keyword.
     *
     * @param keyword The keyword the user wants to search for.
     * @return Tasklist of all tasks containing the keyword.
     * @throws DukeException if no task containing the keyword exists.
     */
    public TaskList find(String keyword) throws DukeException {
        TaskList toReturn = new TaskList();
        for (Task t : this.taskList) {
            if (t.getTaskName().contains(keyword)) {
                toReturn.add(t);
            }
        }
        if (toReturn.getSize() == 0) {
            throw new DukeException(ui.taskDoesNotExistError());
        }
        return toReturn;
    }

    public int getNumDoneTasks() {
        int numDoneTasks = 0;
        for (Task t : this.taskList) {
            if (t.isDone()) {
                numDoneTasks++;
            }
        }
        return numDoneTasks;
    }

    public int getNumDoneTasksWithinWeek() {
        int numDoneTasksWithinWeek = 0;
        for (Task t : this.taskList) {
            if (t.isDone() && t.isDoneWithinPastWeek()) {
                numDoneTasksWithinWeek++;
            }
        }
        return numDoneTasksWithinWeek;
    }

    public int getNumUpcomingTasksWithinWeek() {
        int numUpcomingTasks = 0;
        for (Task t : this.taskList) {
            if (!t.isDone() && t.isWithinNextWeek()) {
                numUpcomingTasks++;
            }
        }
        return numUpcomingTasks;
    }


}
