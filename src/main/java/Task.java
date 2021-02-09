/**
 * Task class there are different kinds of tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     *
     * @param description the task description
     * @throws EmptyArgumentException thrown if task description is empty
     */
    Task(String description) throws EmptyArgumentException {
        if(description.isEmpty()) throw new EmptyArgumentException();
        this.description = description;
    }

    /**
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + description;
    }
}
