package ip.src.main.java;

/**
 * ToDo class is a type of Task that has attribute String content.
 *
 */

public class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    /**
     *Edits the content of the ToDo task by cloning the old ToDo task.
     *
     * @param newContent New content given by user.
     * @return A clone of the old ToDo task with the new content change.
     */
    public ToDo editContent (String newContent) {
        ToDo newTask = new ToDo(newContent);
        newTask.done = this.done;
        return newTask;
    }

    /**
     * toString() of ToDo Class.
     *
     * @return toString() representation of a ToDo object with its done status and content.
     */
    @Override
    public String toString() {
        if (!this.done) {
            return "T | 0 | " + super.toString();
        } else {
            return "T | 1 | " + super.toString();
        }
    }
}
