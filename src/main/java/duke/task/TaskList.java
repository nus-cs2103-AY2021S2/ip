package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Adds task to task list.
     *
     * @param taskType Type of task.
     * @param description Description of task.
     * @param isReadingFile True if a file is being read, false if a file is not being read.
     * @param storage Storage.
     * @param isDone Task is done or not.
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
        } else {
            assert false : "Task cannot be added because it is not a todo, deadline or event task.";
        }

        if (isReadingFile) {
            if (isDone) {
                newTask.markAsDone();
            }
            this.tasks.add(newTask);
            return "";
        } else {
            storage.checkForDuplicate(newTask.toString());
            this.tasks.add(newTask);
            storage.appendToFile("data/duke.txt", newTask.toString());
            return ("Got it. I've added this task: \n"
                    + "  " + newTask + "\n"
                    + "Now you have " + this.tasks.size() + " tasks in the list.");
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
        if (taskNo > this.tasks.size()) {
            throw new DukeException("☹ OOPS!!! This task number does not exist.");
        }

        String message;

        message = "Noted. I've removed this task:\n";
        message += "  " + this.tasks.get(taskNo - 1);
        this.tasks.remove(taskNo - 1);
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
            if (tasks.get(i) instanceof DeadlineTask) {
                LocalDate deadlineDate = ((DeadlineTask) tasks.get(i)).getDeadlineDate();
                if (deadlineDate.compareTo(date) == 0) {
                    list.add(tasks.get(i));
                }
            } else if (tasks.get(i) instanceof EventTask) {
                LocalDate eventDateDate = ((EventTask) tasks.get(i)).getEventDateDate();
                if (eventDateDate.compareTo(date) == 0) {
                    list.add(tasks.get(i));
                }
            } else {
                assert false : "Tasks on this date cannot be printed because it is not a deadline or event task.";
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
