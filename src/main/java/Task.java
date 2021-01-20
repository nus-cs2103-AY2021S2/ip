package main.java;

public class Task {
    String name;
    Boolean status;

    Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void setDone() {
        this.status = true;
    }

    @Override
    public String toString() {
        String statusText;
        statusText = status ? "\u2713" : "\u2718";
        return statusText + " " + name;
    }
}
