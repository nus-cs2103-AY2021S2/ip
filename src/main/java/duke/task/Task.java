package main.java.duke.task;

import java.time.LocalDate;

public class Task {
    String name;
    Boolean status;
    String addMessage;
    LocalDate date;

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

    /**
     * Retrieves date of task
     * @return LocalDate: date of task
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Retrieves status of task
     * @return boolean: true (done), false (not done)
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

    @Override
    public String toString() {
        String statusText;
        statusText = status ? "\u2713" : "\u2718";
        return statusText + " " + name;
    }
}
