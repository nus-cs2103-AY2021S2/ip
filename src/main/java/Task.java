// Original definition from the webpage of course
// https://nus-cs2103-ay2021s2.github.io/website/schedule/week2/project.html

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
