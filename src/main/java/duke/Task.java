package duke;
import java.time.LocalDate;

/**
 * The class in which Deadline, Event and ToDo inherits from.
 * It contains several common methods shared by all subclasses.
 */
abstract class Task {
    // The name of the task.
    protected final String taskName;
    // Whether the task is completed.
    protected boolean isDone;

    /**
     * Constructor to initialise the name of the task as well as set the task to not completed
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Overloaded constructor that includes an additional argument to set the task to completed
     *
     * @param taskName the name of the task
     * @param isDone that indicates whether the task has already been completed
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    protected void completeTask() {
        this.isDone = true;
    }

    /**
     * Takes in a LocalDate object and returns the date of the object in a special format.
     *
     * @param date the LocalDate object to print the date of.
     * @return the string representation of the date of the object in the special format.
     */
    public static String printDate(LocalDate date) {
        String month = date.getMonth().toString().substring(0, 3);
        String monthInLowerCase = month.substring(0, 1) + month.substring(1, 3).toLowerCase();
        String[] parsedDate = date.toString().split("-");
        String year = parsedDate[0];
        String day = parsedDate[2];
        return monthInLowerCase + " " + day + " " + year;
    }

    /**
     * Getter method that returns true if the task has been completed, and false otherwise.
     *
     * @return a boolean value that indicates if the task has been completed
     */
    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Getter method that returns the name of the task.
     *
     * @return a string representation of the name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }
}
