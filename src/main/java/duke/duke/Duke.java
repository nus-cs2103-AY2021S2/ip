package duke.duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final TaskList list;

    public Duke() {
        this.list = new TaskList();

    }

    public Duke(TaskList list) {
        this.list = list;
    }

    public void addTask(String message, String type, String date) {
        Task task = null;
        switch (type) {
        case "todo":
            task = new Todo(message);
            break;
        case "deadline":
            task = new Deadline(message, date);
            break;
        case "event":
            task = new Event(message, date);
            break;
        }

        list.addItem(task);
        Ui.showAddTaskMessage(task, list.getLst());
    }

    public void removeTask(String id) {
        int n = Integer.parseInt(id) - 1;
        Task task = list.getLst().get(n);
        list.removeItem(n);
        Ui.showRemoveTaskMessage(task, list.getLst());
    }

    public void markAsDone(String id) {
        int n = Integer.parseInt(id) - 1;
        list.doneTask(n);
        Ui.showDoneTaskMessage(list.getLst(), n);

    }

    public void showTasks() {
        Ui.showTasksToUser(list.getLst());
    }

    public TaskList getList() {
        return list;
    }

    public String getLogo() {
        return LOGO;
    }

    public int getNumberOfTasks() {
        return list.size();
    }

}
