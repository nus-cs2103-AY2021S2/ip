package duke;

/**
 * Class ToDo represents a thing to do in the taskList that Duke can taken note.
 * ToDo object only has it name specified without date and time.
 */
class ToDo extends Task {

    /**
     * Returns a todo with the specified name (description).
     *
     * @param taskName name (description) of todo.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a String, which is the expression of a todo.
     *
     * @return todo expression.
     */
    @Override
    public String printTask() {
        String ans;
        if (this.isTaskDone()) {
            ans = "[T][X] " + this.getTaskName();
        } else {
            ans = "[T][ ] " + this.getTaskName();
        }
        return ans;
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    @Override
    public boolean isTaskDone() {
        return super.isTaskDone();
    }
}
