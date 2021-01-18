package core;

public class Todo extends Task{
    public Todo(String desc) throws IllegalArgumentException {
        super(desc.trim());
        if(this.taskDescription.isEmpty()) {
            throw new IllegalArgumentException("Empty Description for task !!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
