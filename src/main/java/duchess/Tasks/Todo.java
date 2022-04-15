package duchess.Tasks;

public class Todo extends Task {

    /** Constructs new Todo task
     *
     * @param name todo task name
     */

    public Todo(String name) {
        super(name);
        this.cat = 'T';
    }

    @Override
    public String toString() {
        return "[" + this.cat + "] " + super.toString();
    }
}
