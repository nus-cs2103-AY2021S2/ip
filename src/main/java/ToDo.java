public class ToDo extends Task {

    public ToDo(String description, String type) {
        super(description, type);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
