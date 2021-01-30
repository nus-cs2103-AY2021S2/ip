class Todo extends Task {
    protected boolean isDone;
    protected final static String type = "[T]";

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() + super.toString();
    }

    /**
     * Parses a todo description in duke.txt.
     *
     * @param record todo description in duke.txt
     * @return a todo object
     */
    public static Todo parseTodo(String record) {
        if (record.contains("\u2713")) {
            String[] taskSeg = record.split("\u2713 ");
            String taskContent = taskSeg[taskSeg.length - 1];
            Todo myTask = new Todo(taskContent);
            myTask.markAsDone();
            return myTask;
        } else {
            String[] taskSeg = record.split("\u2718 ");
            String taskContent = taskSeg[taskSeg.length - 1];
            return new Todo(taskContent);
        }

    }

}
