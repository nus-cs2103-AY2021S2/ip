package duke.task;

import duke.FileManager;
import duke.Storage;
import duke.exception.DukeException;

import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected List<Task> taskList;

    /**
     * Creates a new instance of <code>TaskList</code> when no existing tasks are available.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a new instance of <code>TaskList</code> when existing tasks are available.
     *
     * @param existingTaskList TaskList with existing tasks.
     */
    public TaskList(TaskList existingTaskList) {
        this.taskList = existingTaskList.taskList;
    }

    /**
     * Returns this task list.
     *
     * @param taskNo Task number.
     * @return Task for the given task number.
     */
    public Task getTask(int taskNo) {
        return this.taskList.get(taskNo - 1);
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * Adds task to task list.
     *
     * @param taskType Type of task.
     * @param description Description of task.
     * @param isReadingFile True if a file is being read, false if a file is not being read.
     * @param storage Storage.
     * @throws DukeException If description is not given in the correct format.
     * @throws IOException If there are any input and output issues.
     */
    public void addTask(TaskType taskType, String description, boolean isReadingFile, Storage storage) throws DukeException, IOException {
        Task newTask = new Task(description);
        if (taskType == TaskType.TODO) {
            newTask = new ToDoTask(description);
        } else if (taskType == TaskType.DEADLINE) {
            String[] descriptionArr = description.split(" /by ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }

            Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
            Matcher matcher = pattern.matcher(descriptionArr[1]);
            if (!matcher.find()) {
                throw new DukeException("Your deadline is given in the wrong format! "
                        + "Please make sure it is in the following format: YYYY-MM-DD HH:MM");
            }

            newTask = new DeadlineTask(descriptionArr[0], descriptionArr[1]);
        } else if (taskType == TaskType.EVENT) {
            String[] descriptionArr = description.split(" /at ");
            if (descriptionArr.length == 1) {
                throw new DukeException("Your description is not given in the correct format!");
            }

            Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
            Matcher matcher = pattern.matcher(descriptionArr[1]);
            if (!matcher.find()) {
                throw new DukeException("Your event date is given in the wrong format! "
                        + "Please make sure it is in the following format: YYYY-MM-DD HH:MM");
            }

            newTask = new EventTask(descriptionArr[0], descriptionArr[1]);
        }
        this.taskList.add(newTask);
        if (!isReadingFile) {
            storage.appendToFile("data/duke.txt", newTask.toString());
            System.out.println("Got it. I've added this task: \n"
                    + "  " + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " tasks in the list.");
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param taskNo Task number.
     * @param storage Storage.
     * @throws DukeException If task number does not exist.
     */
    public void deleteTask(int taskNo, Storage storage) throws DukeException, IOException {
        if (taskNo > this.taskList.size()) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this.taskList.get(taskNo - 1));
        this.taskList.remove(taskNo - 1);
        String taskOrTasks = (this.taskList.size() <= 1)
                ? " task"
                : " tasks";
        System.out.println("Now you have " + this.taskList.size() + taskOrTasks + " in the list.");
        FileManager.deleteLine("data/duke.txt", taskNo, storage);

    }

    /**
     * Prints tasks in task list.
     */
    public void printTaskList() {
        if (this.taskList.size() == 0) {
            System.out.println("There are currently no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println(i + "." + this.taskList.get(i - 1));
            }
        }
    }

    /**
     * Prints tasks that match the given date.
     *
     * @param date Date to be matched to tasks.
     */
    public void printTasksOn(LocalDate date) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (taskList.get(i) instanceof DeadlineTask) {
                LocalDate deadlineDate = ((DeadlineTask) taskList.get(i)).getDeadlineDate();
                if (deadlineDate.compareTo(date) == 0) {
                    System.out.println(taskList.get(i));
                }
            } else if (taskList.get(i) instanceof EventTask) {
                LocalDate eventDateDate = ((EventTask) taskList.get(i)).getEventDateDate();
                if (eventDateDate.compareTo(date) == 0) {
                    System.out.println(taskList.get(i));
                }
            }
        }
    }

}
