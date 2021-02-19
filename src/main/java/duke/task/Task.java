package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task class to initialize and manage different type of tasks
 */
public class Task {
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected String description;
    //Task status
    protected boolean isCompleted;

    public Task(){

    }

    /** Creates a task with description
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /** Creates a task with description and status
     * @param description task description
     * @param isCompleted task status
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Set task status to completed
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /** Returns task status in the form of a tick or a cross
     * @return a tick if the task status is completed or a cross if the task status is incomplete
     */
    public String getStatus() {
        return (this.isCompleted ? "\u2713" : "\u2718");
    }

    /** Returns task description of task
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Returns a customized representation of the task to be added to the data file
     * @return string representation of a task
     */
    public String changeFormat() {
        return "," + this.isCompleted + "," + this.getTaskDescription();
    }

    /** Convert the task information stored in data file to task objects
     * @param taskDescription task description from data file
     * @return task
     */
    public Task changeToTaskFormat(String taskDescription) {

        assert taskDescription != null : "Task identifier from data file should be specified";
        if (taskDescription.charAt(0) == 'T') {
            String[] tasks = taskDescription.split(",");

            return new ToDo(tasks[2], Boolean.parseBoolean(tasks[1]));
        } else if (taskDescription.charAt(0) == 'D') {
            String[] tasks = taskDescription.split(",");
            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);

            return new Deadline(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime);
        } else if (taskDescription.charAt(0) == 'E') {
            String[] tasks = taskDescription.split(",");
            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);
            LocalTime endTime = LocalTime.parse(tasks[5], timeFormatter);

            return new Event(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime, endTime);
        } else {
            return new Task();
        }
    }

    /**
     * Check for duplication of task
     * @param task
     * @return boolean true if task is duplicated
     */

    public boolean isSameTask(Task task) {
        return this.getTaskDescription().equals(task.getTaskDescription());
    }

    /**
     * Returns a customized representation of the task to the user
     * @return string representation of task to be displayed to the user
     */
    @Override
    public String toString() {
        return this.getTaskDescription() + "[" + this.getStatus() + "] ";
    }
}
