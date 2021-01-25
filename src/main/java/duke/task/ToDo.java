package duke.task;


/**
 *  Create a todo class
 */
public class ToDos extends Task {

    /**Constructor to create todo object for retrieval of task from data file
     * @param title
     * @param b boolean
     */
    public ToDos(String title, Boolean b) {
        super(title, b);
    }

    /**Constructor to create todo object
     * @param title
     */
    public ToDos(String title) {
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
    public String changeFormat(){
        return "T" + super.changeFormat();
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
