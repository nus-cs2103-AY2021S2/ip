package main.java;

public class Task {
    String name;
    Boolean status;
    String addMessage;

    Task(String name) {
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
