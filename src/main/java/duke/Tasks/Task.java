package duke.Tasks;

public class Task {
    /** Name of task */
    protected String name;

    /** Boolean to mark whether task is completed */
    protected boolean completed;

    /** Label the category of the task */
    protected char cat;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /** Returns the category of task
     *
     * @return Category label of task as a char
     */
    public char getCat() {
        return this.cat;
    }

    /** Returns the name of task
     *
     * @return Name of task as string
     */
    public String getName() {
        return this.name;
    }

    /** Returns boolean of whether task has been completed
     *
     * @return Boolean
     * */
    public boolean getCompleted() {
        return this.completed;
    }

    /** Returns an icon to mark whether task has been completed
     *
     * @return Icon as string
     * */
    public String getStatusIcon() {
        return (completed ? "X" : " "); //return tick or blank
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
