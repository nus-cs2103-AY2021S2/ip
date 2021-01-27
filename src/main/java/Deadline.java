package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    @JsonProperty
    private LocalDateTime deadline;

    protected Deadline() {
        super();
    }

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("[D][%s] %s (by: %s)", done ? "X" : " ", taskName, this.deadline.format(formatter));
    }
}
