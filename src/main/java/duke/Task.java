package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String todo;
    protected boolean isDone;
    protected List<String> tags;

    /**
     * Instantiates a task.
     *
     * @param s The name of the task to be added.
     */
    public Task(String s) {
        this.todo = s;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getTodo() {
        return this.todo;
    }

    /**
     * Returns a String form of the current Task to be saved onto the hard disk.
     *
     * @return the Task as a String to be saved.
     */
    public String saveToData() {
        if (this.isDone) {
            return ("T | 1 | " + todo + " | " + saveTags());
        } else {
            return ("T | 0 | " + todo + " | " + saveTags());
        }
    }

    /**
     * Returns a nicely formatted String from the given LocalDateTime.
     *
     * @param date Date to be formatted.
     * @return inputted date as a String.
     */
    public String dateFormat(LocalDateTime date) {
        return (date.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")));
    }

    public void setTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns the current tags as a String.
     *
     * @return Current tags.
     */
    protected String showTags() {
        String toReturn = "";
        for (String s : tags) {
            toReturn += ("#" + s + " ");
        }
        return toReturn;
    }

    /**
     * Returns the current tags as a String to be saved.
     *
     * @return A string to be saved in Duke.txt.
     */
    protected String saveTags() {
        String toReturn = "";
        for (String s : tags) {
            toReturn += (s + " ");
        }
        return toReturn;
    }

    @Override
    public String toString() {
        if (!this.isDone) {
            return ("[T][ ] " + todo + " " + showTags());
        } else {
            return ("[T][X] " + todo + " " + showTags());
        }
    }

}
