public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean done) {
        super(description, done);
    }

    /**
     * Creates a to-do task and returns it.
     *
     * @param taskInfo information of to-do task.
     * @return new to-do task.
     * @throws DukeMissingDescriptionException if task info is empty.
     */
    public static ToDo create(String taskInfo) throws DukeMissingDescriptionException {
        if (taskInfo.equals(" ") || taskInfo.equals("")) {
            throw new DukeMissingDescriptionException("todo");
        } else {
            return new ToDo(taskInfo);
        }
    }

    /**
     * Changes the task state to be finished.
     *
     * @return new finished to-do task.
     */
    @Override
    public ToDo finishTask() {
        return new ToDo(description, true);
    }

    /**
     * Return string of to-do task name and info.
     * Format is for saving task into text file.
     *
     * @return string format of to-do info.
     */
    @Override
    public String saveTask() {
        return String.format("T | %s | %s\n",
                super.getStatusIcon(), description);
    }

    /**
     * Returns string of event task name and info.
     * Format is for the display on the list.
     *
     * @return string format of task's info.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}