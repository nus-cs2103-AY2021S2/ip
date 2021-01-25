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

    public void setDone() {
        this.status = true;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public boolean getStatus() {
        return this.status;
    }

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
