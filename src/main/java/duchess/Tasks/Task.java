package duchess.Tasks;

public class Task {
    /** Name of task */
    protected String name;

    /** Boolean to mark whether task is completed */
    protected boolean isCompleted;

    /** Label the category of the task */
    protected char cat;

    /** Constructs a new task
     *
     * @param name of task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /** Returns the category of task
     *
     * @return Category label of task as a char
     */
    public char getCategory() {
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
        return this.isCompleted;
    }

    /** Marks task as completed */
    public void checkTask() {
        this.isCompleted = true;
    }

    /** Returns an icon to mark whether task has been completed
     *
     * @return Icon as string
     * */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
