package duke;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The class that contains the task list. It has operations to add/delete
 * tasks in the list.
 */
class TaskList {
    private ArrayList<Task> taskList;

    /**
     * If there is no existing data on the local hard disk, Dukebot will startup with an
     * empty task list. If there is an existing text file, then Dukebot will copy over the
     * text file into the task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        File file = new File("./data/duke.txt");
        try {
            if (file.exists()) {
                Storage.convert(file, this.taskList);
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Iterates over the task list and prints out all the tasks.
     */
    public String listTask() {
        String dukeMessage = "";
        dukeMessage += "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            dukeMessage += "\n";
            Task currTask = taskList.get(i);
            dukeMessage += (i + 1) + "." + currTask;
        }
        return dukeMessage;
    }

    /**
     * Set the task at the particular index as done.
     *
     * @param index the index of the task which is completed
     * @throws DukeException is thrown when the index given is invalid
     */
    protected String doneTask(int index) throws DukeException {
        String dukeMessage = "";
        if (this.taskList.size() < index) {
            dukeMessage += "Task not found";
            return dukeMessage;
        }
        Task currTask = this.taskList.get(index - 1);
        currTask.completeTask();
        dukeMessage += "Nice! I've marked this task as done:\n " + currTask;
        Storage.update(this.taskList);
        return dukeMessage;
    }

    /**
     * Deletes the task at the particular index.
     *
     * @param index the index of the task to be deleted
     */
    public String delete(int index) {
        String dukeMessage = "";
        int deleteIndex = index;
        if (this.taskList.size() >= deleteIndex) {
            dukeMessage += "Noted. I've removed this task:\n";
            dukeMessage += "  " + this.taskList.remove(deleteIndex - 1);
            dukeMessage += this;
        } else {
            dukeMessage += "Task not found";
        }
        Storage.update(this.taskList);
        return dukeMessage;
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param taskName the name of the todo task
     */
    public String addToDo(String taskName) {
        String dukeMessage = "";
        ToDo newTask = new ToDo(taskName);
        this.taskList.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n";
        dukeMessage += this;
        Storage.update(this.taskList);
        return dukeMessage;
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param taskName the name of the event task
     * @param date the date on which the event takes place
     */
    public String addEvent(String taskName, LocalDate date) {
        String dukeMessage = "";
        Event newTask = new Event(taskName, date);
        this.taskList.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n";
        dukeMessage += this;
        Storage.update(this.taskList);
        return dukeMessage;
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param taskName the name of the deadline task
     * @param date the date on which the deadline is due
     */
    public String addDeadline(String taskName, LocalDate date) {
        String dukeMessage = "";
        Deadline newTask = new Deadline(taskName, date);
        this.taskList.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n";
        dukeMessage += this;
        Storage.update(this.taskList);
        return dukeMessage;
    }

    public String find(String keyWord) {
        String dukeMessage = "";
        boolean isFirst = true;
        for (Task currTask : taskList) {
            if (currTask.getTaskName().contains(keyWord)) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    dukeMessage += "\n";
                }
                dukeMessage += currTask;
            }
        }
        return dukeMessage;
    }

    /**
     * Displays the number of tasks in the list in a special format.
     *
     * @return a string that shows the number of tasks in the list
     */
    @Override
    public String toString() {
        String taskSingularOrPlural = "";
        if (this.taskList.size() == 1) {
            taskSingularOrPlural = "task";
        } else {
            taskSingularOrPlural = "tasks";
        }
        return "Now you have " + this.taskList.size() + " "
                + taskSingularOrPlural + " in the list";
    }
}
