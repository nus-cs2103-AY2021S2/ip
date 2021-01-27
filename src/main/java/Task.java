public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static String delimiter = ";;";
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // used by parsing functions
    protected Task(String desc, Boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "/" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    // parse from task object to format for text file
    public abstract String unparse();

    // parse from text file to become a task object todo - this needs to be static so can't be abstract
    // public abstract Task parse(String oneLine);

}
