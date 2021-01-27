package kelbot;

public class TodoTask extends Task {
    
    /*
     * Initializes Todo Task
     *
     * @param name The name of the Todo Task
     */
    
    public TodoTask(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
