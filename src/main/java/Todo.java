public class Todo extends Task {

    public Todo(String input) {
        super(input);
        this.type = TaskEnum.T;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
