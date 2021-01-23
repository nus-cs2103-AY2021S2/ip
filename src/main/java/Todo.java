public class Todo extends Task {

    public Todo(String input) {
        super(input);
        this.type = TaskEnum.T;
        this.date = null;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
