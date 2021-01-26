package duke;
/**
 *  A type of task that is to be done.
 */

public class ToDo extends Task{
    public ToDo(String description){
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[" + this.getLetterCode() + "]" + super.toString();
    }
}
