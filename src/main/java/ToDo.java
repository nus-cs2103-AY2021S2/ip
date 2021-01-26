public class ToDo extends Task {

    private final String type = "T";

    public ToDo(String description) {
        super(description);
    }

    public String getType(){
        return this.type;
    }

    @Override
    public String toString() {
//        return "[T]" + super.toString();
        return type + separator + super.toString();
    }
}
