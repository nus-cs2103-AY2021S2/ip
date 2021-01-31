package duke.task;


/**
 *  Create a todo class
 */

public class ToDo extends Task {

    public ToDo(String title, Boolean b) {
        super(title, b);
    }

    public ToDo(String title) {
        super(title);
    }

    /** Change representation of task to be added to data file
     * @return String
     */

    @Override
    public String changeFormat() {
        return "T" + super.changeFormat();
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return " Todo:" + super.toString();
    }
}
