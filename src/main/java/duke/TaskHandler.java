package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.FileIoException;
import duke.exceptions.IndexOutOfRangeException;
import duke.exceptions.TaskDoneException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * Represents a task handler that handles the tasks
 */
public class TaskHandler {
    private ArrayList<Task> tasks;

    public TaskHandler() {
        this.tasks = new ArrayList<>();
    }

    public void clearTaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void addTodoTask(String taskName) {
        tasks.add(new TodoTask(taskName));
    }

    public void addDeadlineTask(String taskName, LocalDate deadline) {
        tasks.add(new DeadlineTask(taskName, deadline));
    }

    public void addEventTask(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        tasks.add(new EventTask(taskName, startTime, endTime));
    }

    /**
     * Returns the Task that is done
     *
     * @return A Task
     */
    public Task markTaskAsDone(int index) throws IndexOutOfRangeException, TaskDoneException {
        try {
            Task task = tasks.get(index - 1);
            if (!task.getIsDone()) {
                task.setTaskDone();
                return task;
            } else {
                throw new TaskDoneException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfRangeException();
        }
    }

    /**
     * Returns the Task being deleted
     *
     * @return A Task
     */
    public Task deleteTask(int index) throws IndexOutOfRangeException {
        try {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfRangeException();
        }
    }

    /**
     * Returns an arraylist of tasks contains the keyword
     *
     * @return An ArrayList of Task
     */
    public ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> matchTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(keyWord)) {
                matchTasks.add(task);
            }
        }
        return matchTasks;
    }

    /**
     * Returns an arraylist of tasks of the date specified
     *
     * @return An ArrayList of Task
     */
    public ArrayList<Task> findTaskOfDay(String date) {
        ArrayList<Task> matchTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getType().equals("[D]") || task.getType().equals("[E]")) {
                String[] taskAsString = task.writeToFileFormat().split("\\|");
                String dateAsString = taskAsString[3].substring(0, 11);
                System.out.println(dateAsString);
                if (dateAsString.equals(date)) {
                    matchTasks.add(task);
                }
            }
        }
        return matchTasks;
    }

    /**
     * Reads from the task data file.
     *
     * @param storedTaskList an ArrayList of tasks in file format.
     */
    public void loadTaskList(ArrayList<String> storedTaskList) throws FileIoException {
        for (String eachTask : storedTaskList) {
            String[] words = eachTask.split("\\|");
            String type = words[0].strip();
            boolean isDone = words[1].strip().equals("1");
            String taskName = words[2].strip();

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
            switch (type) {
            case "T":
                Task todoTask = new TodoTask(taskName);
                if (isDone) {
                    todoTask.setTaskDone();
                }
                this.tasks.add(todoTask);
                break;
            case "D":
                LocalDate deadline = LocalDate.parse(words[3].strip(), dateFormat);
                Task deadlineTask = new DeadlineTask(taskName, deadline);
                if (isDone) {
                    deadlineTask.setTaskDone();
                }
                this.tasks.add(deadlineTask);
                break;
            case "E":
                LocalDateTime startTime = LocalDateTime.parse(words[3].strip(), timeFormat);
                LocalDateTime endTime = LocalDateTime.parse(words[4].strip(), timeFormat);
                Task eventTask = new EventTask(taskName, startTime, endTime);
                if (isDone) {
                    eventTask.setTaskDone();
                }
                this.tasks.add(eventTask);
                break;
            default:
                throw new FileIoException();
            }
        }
    }
}
