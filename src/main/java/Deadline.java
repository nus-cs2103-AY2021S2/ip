package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deadline extends Task {
    @JsonProperty
    private String deadline;

    protected Deadline() {
        super();
    }

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? "X" : " ", taskName, this.deadline);
    }
}
