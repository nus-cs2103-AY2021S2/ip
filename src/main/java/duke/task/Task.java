package duke.task;

import java.time.LocalDate;

public class Task {
    protected String name;
    protected Boolean status;
    protected String addMessage;
    protected LocalDate date;

    private final static String SYMBOL_TICK = "\u2713";
    private final static String SYMBOL_CROSS = "\u2718";

    /**
     * Creates a new task based on the given name
     * @param name name of task
     */
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    Task(String name, String addMessage) {
        this(name);
        this.addMessage = addMessage;
    }

    /**
     * This method marks the task as done
     */
    public void setDone() {
        this.status = true;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Retrieves date of task
     * @return LocalDate date of task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Retrieves status of task
     * @return boolean true (done), false (not done)
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Retrieves additional message of task
     * @return additional message of task
     */
    public String getAddMessage() {
        return this.addMessage;
    }

    public String toFileString() {
        return "";
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public void updateDate(LocalDate newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        String statusText;
        statusText = status ? SYMBOL_TICK : SYMBOL_CROSS;
        return statusText + " " + name;
    }
}
