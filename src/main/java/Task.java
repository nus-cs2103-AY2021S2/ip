class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getDescription(){
        return description;
    }

    boolean getIsDone() {
        return isDone;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    void markAsDone(){
        isDone = true;
    }

    @Override
    public String toString(){
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
