package main.java;

public class Task {
    String name;
    Boolean status;

    Task(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String statusText;
        statusText = status ? "\u2713" : "\u2718";
        return statusText + " " + name;
    }
}
