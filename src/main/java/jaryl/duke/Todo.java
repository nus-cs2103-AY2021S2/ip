package jaryl.duke;

public class Todo extends Task {
    public Todo(String description) throws InvalidFormatException {
        super(description, "T");
        if(description.equals(""))
            throw new InvalidFormatException("Please specify task description");
    }

    @Override
    public String toString() {
        return "[" + super.getType() + "]" + super.toString();
    }
}
