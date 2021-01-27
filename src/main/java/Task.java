public abstract class Task {
    protected Boolean isDone;
    protected String taskInfo;

    public Task(String taskInfo) {
        isDone = false;
        this.taskInfo = taskInfo;
    }

    @Override
    public String toString() {
        String s = "";
        if (isDone) {
            s = "[X] ";
        } else {
            s = "[ ] ";
        }
        return s + taskInfo;
    }

    public void completed() {
        isDone = true;
    }

    /**
     * Method that checks if the task description contains
     * the given subString.
     *
     * @param subString the subString to search for inside the task
     *                  description.
     * @return true if task description contains subString, false otherwise.
     */
    public boolean contains(String subString) {
        return taskInfo.contains(subString);
    }

    public abstract String getData();
}
