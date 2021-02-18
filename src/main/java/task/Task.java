package task;

public abstract class Task {

    protected boolean isDone;
    protected String description;
    final String tag;

    /**
     * Task constructor
     *
     * @param description
     * @param tag
     * @return task object
     */
    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getTypeIcon() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String tokenize();

    @Override
    public String toString() {
        return this.description;
    }

}
