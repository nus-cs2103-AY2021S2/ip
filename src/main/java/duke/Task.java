package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class in which Deadline, Event and ToDo inherits from. It contains
 * several common methods shared by all subclasses.
 */
abstract class Task {
    protected final String taskName;
    protected boolean isDone;

    /**
     * Constructor to initialise the name of the task. Also sets the task to
     * not done by default.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        assert (taskName.equals("")) : "Name of task cannot be empty";
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Overloaded constructor to create a task object. It accepts one extra
     * argument to determine if the task is completed.
     *
     * @param taskName The name of the task.
     * @param isDone Whether is task is already completed.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Getter method that returns true if the task has been completed, and false otherwise.
     *
     * @return A boolean value that indicates if the task has been completed.
     */
    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Getter method that returns the name of the task.
     *
     * @return A string representation of the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Setter method to set a task as completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Takes in a LocalDate object and returns the date of the object as a string
     * in the format MMM DD YYYY.
     *
     * @param date The LocalDate object to print the date of.
     * @return A string representation of the date in the format MMM DD YYYY.
     */
    public static String printDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }
}
