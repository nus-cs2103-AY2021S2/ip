package mainclasses;

import java.time.LocalDate;
import java.time.LocalTime;


public class Task {
    protected String description;
    protected boolean done;
    protected TaskEnum type;
    protected LocalDate date;
    protected LocalTime time;

    public Task() {

    }

    public Task(String input) {
        this.description = input;
        done = false;
    }

    public String getDescription() {
        return this.description;
    }

    protected String getOnlyDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean set) {
        this.done = set;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public TaskEnum getType() {
        return type;
    }

    public void setType(TaskEnum type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
