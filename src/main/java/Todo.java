public class Todo extends Task {

    protected String type = "T";

    public Todo(String[] input) {
        super(input);
        for (String s : input) {
            this.description.append(s + " ");
        }
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
