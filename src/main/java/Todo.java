public class Todo extends Task {

    /**
     * Constructor method.
     * @param description String that describes the task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the todo.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}