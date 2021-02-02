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
     * empty task list. If there is an existing text file, then Dukebok will copy over the
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
        String result = "";
        result += "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            result += "\n";
            result += i + 1 + "." + currTask;
        }
        return result;
    }

    /**
     * Set the task at the particular index as done.
     *
     * @param index the index of the task which is completed
     * @throws DukeException is thrown when the index given is invalid
     */
    protected String doneTask(int index) throws DukeException {
        String result = "";
        if (this.taskList.size() >= index) {
            Task currTask = this.taskList.get(index - 1);
            currTask.completeTask();
            result += "Nice! I've marked this task as done:\n";
            result += "  " + currTask;
        } else {
            result += "Task not found";
        }
        Storage.update(this.taskList);
        return result;
    }

    /**
     * Deletes the task at the particular index.
     *
     * @param index the index of the task to be deleted
     */
    public String delete(int index) {
        String result = "";
        int deleteIndex = index;
        if (this.taskList.size() >= deleteIndex) {
            result += "Noted. I've removed this task:\n";
            result += "  " + this.taskList.remove(deleteIndex - 1);
            result += this;
        } else {
            result += "Task not found";
        }
        Storage.update(this.taskList);
        return result;
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param taskName the name of the todo task
     */
    public String addToDo(String taskName) {
        String result = "";
        ToDo newTask = new ToDo(taskName);
        this.taskList.add(newTask);
        result += "Got it. I've added this task:\n";
        result += "  " + newTask + "\n";
        result += this;
        Storage.update(this.taskList);
        return result;
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param taskName the name of the event task
     * @param date the date in which the event takes place
     */
    public String addEvent(String taskName, LocalDate date) {
        String result = "";
        Event newTask = new Event(taskName, date);
        this.taskList.add(newTask);
        result += "Got it. I've added this task:\n";
        result += "  " + newTask + "\n";
        result += this;
        Storage.update(this.taskList);
        return result;
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param taskName the name of the deadline task
     * @param date the date in which the deadline is due
     */
    public String addDeadline(String taskName, LocalDate date) {
        String result = "";
        Deadline newTask = new Deadline(taskName, date);
        this.taskList.add(newTask);
        result += "Got it. I've added this task:\n";
        result += "  " + newTask + "\n";
        result += this;
        Storage.update(this.taskList);
        return result;
    }

    public String find(String keyWord) {
        String result = "";
        boolean isFirst = true;
        for (Task t : taskList) {
            if (t.getTaskName().toString().contains(keyWord)) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    result += "\n";
                }
                result += t;
            }
        }
        return result;
    }

    /**
     * Displays the number of tasks in the list in a special format.
     *
     * @return a string that shows the number of tasks in the list
     */
    @Override
    public String toString() {
        if (this.taskList.size() == 1) {
            return "Now you have " + this.taskList.size() + " task in the list";
        } else {
            return "Now you have " + this.taskList.size() + " tasks in the list";
        }
    }
}
