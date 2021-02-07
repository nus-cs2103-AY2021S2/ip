package duke.tasks;

public class ToDo extends Task {
    protected String taskName;

    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a the task type.
     *
     * @return the String of the type.
     */
    @Override
    public String getTaskType() {
        return "ToDo";
    }


    /**
     * Returns a string of description to be saved in the myDuke.txt.
     *
     * @return A String.
     */
    @Override
    public String toSave() {
        return "T / " + super.isDoneString + super.taskName;
    }

    /**
     * Returns a string of description to be printed out.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
