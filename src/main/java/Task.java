// Original definition from the webpage of course
// https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html

import java.time.format.DateTimeFormatter;

public class Task {
    protected static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected static DateTimeFormatter outfmt = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy");
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this(description, false);
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionCheckbox() {
        return toString();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713": "\u2718");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
