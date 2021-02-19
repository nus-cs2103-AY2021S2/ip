package kobe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private boolean done;
    private String taskName;
    private String type;
    private String condition;
    private LocalDate date;
    private boolean isConditionLocalDate;

    /**
     * Constructor for Task.
     *
     * @param taskName  the name of the task
     * @param type  the type of the task (todo, deadline or event)
     * @param condition  the condition of any deadline or event task, taken in in the form of a String
     */
    public Task(String taskName, String type, String condition) {
        this.done = false;
        this.taskName = taskName;
        this.type = type;
        this.condition = condition;
        this.isConditionLocalDate = false;
    }

    /**
     * Constructor for Task.
     *
     * @param done  adds a task that is marked as done ('[X]')
     * @param taskName  the name of the task
     * @param type  the type of the task (todo, deadline or event)
     * @param condition  the condition of any deadline or event task, taken in in the form of a String
     */
    public Task(boolean done, String taskName, String type, String condition) {
        this.done = done;
        this.taskName = taskName;
        this.type = type;
        this.condition = condition;
        this.isConditionLocalDate = false;
    }

    /**
     * Constructor for Task.
     *
     * @param done  adds a task that is marked as done ('[X]')
     * @param taskName  the name of the task
     * @param type  the type of the task (todo, deadline or event)
     * @param date  the date of any deadline or event task, taken in in the form of a LocalDate.
     */
    public Task(boolean done, String taskName, String type, LocalDate date) {
        this.done = done;
        this.taskName = taskName;
        this.type = type;
        this.condition = "";
        this.date = date;
        this.isConditionLocalDate = true;

    }

    /**
     * Marks the task as done ('[X]')
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Sets the task as done ('[X]')
     *
     * @param task  the task to be marked as done
     * @return the task that has been marked as done
     */
    public static Task setAsDone(Task task) {
        task.done = true;
        return task;
    }

    /**
     * Gets the status of the task (if it is done or not)
     *
     * @return  a boolean indicating if the task is done or not
     */
    public boolean getDoneStatus() {
        return this.done;
    }

    /**
     * Gets the task name
     *
     * @return  the task name of that task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the task type
     *
     * @return  the task name of that task
     */
    public String getTaskType() {
        return this.type;
    }

    /**
     * Rewrites the task into a formatted string that is understood by the parser.
     */
    @Override
    public String toString() {
        String doneString = "[ ]";
        String typeString = "[ ]";
        String conditionString = "";

        if (done) {
            doneString = "[X]";
        }

        if (this.type.equals("todo")) {
            typeString = "[T]";
        } else if (this.type.equals("deadline")) {
            typeString = "[D]";
            if (!this.condition.equals("")) {
                conditionString = "(by: " + condition.substring(0) + ")";
            } else if (isConditionLocalDate) { //convert LocalDate to parsed date
                conditionString = "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
            }
        } else if (this.type.equals("event")) {
            typeString = "[E]";
            if (!this.condition.equals("")) {
                conditionString = "(at: " + condition.substring(0) + ")";
            } else if (isConditionLocalDate) { //convert LocalDate to parsed date
                conditionString = "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
            }
        } else {}

        return typeString + doneString + " " + taskName + " " + conditionString;
    }
}