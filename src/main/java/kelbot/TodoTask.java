package kelbot;

public class TodoTask extends Task {
    
    /*
     * Initializes Todo Task
     */
    
    public TodoTask(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
