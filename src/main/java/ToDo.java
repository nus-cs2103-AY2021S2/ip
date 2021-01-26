public class ToDo extends Task {

    private final String type = "T";

    public ToDo(String description) {
        this(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getType(){
        return this.type;
    }

    @Override
    public String toString() {
        return type + separator + super.toString();
    }
}
