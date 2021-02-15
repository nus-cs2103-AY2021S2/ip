package duke;
import java.time.LocalDate;

/**
 * The class in which Deadline, Event and ToDo inherits from. It contains
 * several common methods shared by all subclasses.
 */
abstract class Task {
    // The name of the task.
    protected final String taskName;
    // Whether the task is completed.
    protected boolean isDone;

    /**
     * Constructor to initialise the name of the task. Also sets the task to
     * not done by default.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
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
     * Takes in a LocalDate object and returns the date of the object in a
     * specific format.
     *
     * @param date The LocalDate object to print the date of.
     * @return A string representation of the date in the specified format.
     */
    public static String printDate(LocalDate date) {
        String monthOfDate = date.getMonth().toString().substring(0, 3);
        String monthInLowerCase = monthOfDate.substring(0, 1);
        monthInLowerCase += monthOfDate.substring(1, 3).toLowerCase();
        String[] parsedDate = date.toString().split("-");
        String yearOfDate = parsedDate[0];
        String dayOfDate = parsedDate[2];
        return monthInLowerCase + " " + dayOfDate + " " + yearOfDate;
    }
}
