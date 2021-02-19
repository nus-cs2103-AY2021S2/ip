package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.FileManager;
import duke.Storage;
import duke.exception.DukeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * Creates a new instance of <code>TaskList</code> when no existing tasks are available.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of <code>TaskList</code> when existing tasks are available.
     *
     * @param existingTaskList TaskList with existing tasks.
     */
    public TaskList(TaskList existingTaskList) {
        this.tasks = existingTaskList.tasks;
    }

    /**
     * Returns this task list.
     *
     * @param taskNo Task number.
     * @return Task for the given task number.
     */
    public Task getTask(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

    public int getTaskListSize() {
        return this.tasks.size();
    }

    public boolean hasTaskNumber(int taskNo) {
        return (taskNo > 0) && (taskNo <= this.tasks.size());
    }

    /**
     * Adds task to task list.
     *
     * @param taskType Type of task.
     * @param description Description of task.
     * @param isDone Task is done or not.
     * @param isReadingFile True if a file is being read, false if a file is not being read.
     * @param storage Storage.
     *
     * @return Output string.
     * @throws DukeException If description is not given in the correct format.
     * @throws IOException If there are any input and output issues.
     */
    public String addTask(TaskType taskType, String description, boolean isDone, boolean isReadingFile, Storage storage)
            throws DukeException, IOException {
        Task newTask = new Task(description);
        if (taskType == TaskType.TODO) {
            newTask = new ToDoTask(description);
        } else if (taskType == TaskType.DEADLINE) {
            DeadlineTask.checkFormat(description);
            String[] descriptionArr = description.split(" /by ");
            String info = descriptionArr[0];
            String dateTime = descriptionArr[1];
            newTask = new DeadlineTask(info, dateTime);
        } else if (taskType == TaskType.EVENT) {
            EventTask.checkFormat(description);
            String[] descriptionArr = description.split(" /at ");
            String info = descriptionArr[0];
            String dateTime = descriptionArr[1];
            newTask = new EventTask(info, dateTime);
        }

        if (isDone) {
            newTask.markAsDone();
        }

        this.tasks.add(newTask);

        if (!isReadingFile) {
            storage.appendToFile("data/duke.txt", newTask.toString());
            return ("Got it. I've added this task: \n"
                    + "  " + newTask + "\n"
                    + "Now you have " + this.tasks.size() + " tasks in the list.");
        } else {
            return "";
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param taskNo Task number.
     * @param storage Storage.
     * @return Output string.
     * @throws DukeException If task number does not exist.
     */
    public String deleteTask(int taskNo, Storage storage) throws DukeException, IOException {
        if (!this.hasTaskNumber(taskNo)) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }

        String message;
        int taskIndex = taskNo - 1;

        message = "Noted. I've removed this task:\n"
                + "  " + this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        String taskOrTasks = (this.tasks.size() <= 1)
                ? " task"
                : " tasks";
        message += "\nNow you have " + this.tasks.size() + taskOrTasks + " in the list.";
        FileManager.deleteLine("data/duke.txt", taskNo, storage);

        return message;

    }



    /**
     * Prints tasks in task list.
     *
     * @return Output string.
     */
    public String printTaskList() {
        if (this.tasks.size() == 0) {
            return "There are currently no tasks in your list!";
        } else {
            String listOfTasks = "Here are the tasks in your list:\n";
            for (int i = 1; i <= this.tasks.size(); i++) {
                listOfTasks += i + "." + this.tasks.get(i - 1) + "\n";
            }
            return listOfTasks;
        }
    }

    /**
     * Prints tasks without header and number labels.
     *
     * @return Output string.
     */
    public String printTaskListWithoutNumbers() {
        String listOfTasks = "";
        for (Task task : this.tasks) {
            listOfTasks += task + "\n";
        }
        return listOfTasks;
    }

    /**
     * Prints tasks that match the given date.
     *
     * @param date Date to be matched to tasks.
     * @return Output string.
     */
    public String printTasksOn(LocalDate date) {
        List<Task> list = new ArrayList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof DeadlineTask) {
                LocalDate existingDate = ((DeadlineTask) task).getDeadlineDate();
                if (existingDate.compareTo(date) == 0) {
                    list.add(task);
                }
            } else if (task instanceof EventTask) {
                LocalDate existingDate = ((EventTask) task).getEventDateDate();
                if (existingDate.compareTo(date) == 0) {
                    list.add(task);
                }
            } else {
                continue;
            }

        }

        if (list.size() == 0) {
            return "There are no tasks with this date!";
        } else {
            String listOfTasks = ("Here are the tasks on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " in your list:\n");
            for (Task task : list) {
                listOfTasks += task + "\n";
            }
            return listOfTasks;
        }
    }

    /**
     * Prints tasks that match the given keyword.
     *
     * @param keyword Keyword to be matched to tasks.
     * @return Output string.
     */
    public String printTasksWith(String keyword) {
        List<Task> list = new ArrayList();
        for (Task task : this.tasks) {
            if (task.description.contains(keyword)) {
                list.add(task);
            }
        }

        if (list.size() == 0) {
            return "There are no matching tasks with this keyword!";
        } else {
            String listOfTasks = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= list.size(); i++) {
                listOfTasks += i + "." + list.get(i - 1) + "\n";
            }
            return listOfTasks;
        }
    }

}
