package duke.task;

import java.time.LocalDateTime;

/**
 * Class Task which keeps track of the details of the task
 * and whether the task is done.
 */
public abstract class Task {
    private String details;
    private boolean done;

    /**
     * Takes in details and creates a Task object.
     *
     * @param name details of the task
     */
    public Task(String name) {
        this.details = name;
        this.done = false;
    }


    @Override
    public String toString() {
        if (this.done) {
            String toPrint = String.format("[X] %s", this.details);
            assert !toPrint.isEmpty() : "Something should be printed.";
            return toPrint;
        } else {
            String toPrint = String.format("[ ] %s", this.details);
            assert !toPrint.isEmpty() : "Something should be printed.";
            return toPrint;
        }
    }

    /**
     * Ammends if the task is done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Returns details of the task.
     *
     * @return details of the task
     */
    public String getTaskDetails() {
        return this.details;
    }

    public String getTaskType() {
        return "";
    }

    /**
     * Method to ammend task details
     *
     * @params newDetails to be updated
     */
    public void updateTaskDetails(String newDetails) {
        this.details = newDetails;
    }

    /**
     * Method to ammend task details
     *
     * @params newDetails to be updated
     */
    public abstract void updateTaskDateAndTime(LocalDateTime newDateTime);


    public boolean isEmpty() {
        return false;
    }
}



