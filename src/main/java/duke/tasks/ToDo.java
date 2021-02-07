package duke.tasks;


/**
 * A children of the Task class to be used as basic task types
 */
public class ToDo extends Task {

    /**
     * Default constuctor when a todo task is added.
     *
     * @param info, the details of the task
     */
    public ToDo (String info) {
        super(info, taskType.ToDo);

    }

    /**
     * Secondary constructor used when a todo task is loaded
     * from a .txt file
     * @param info, name of the task
     * @param isDone, boolean value indicating whether the task is done
     */
    public ToDo(String info, boolean isDone) {
        super(info, taskType.ToDo, isDone);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}