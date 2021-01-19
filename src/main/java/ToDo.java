/**
 *  A type of task that is to be done.
 */

public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
