public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String tags) {
        super(description, tags);
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s\n", Command.TODO, super.toFileString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s%s", super.toStringWithoutTags(), super.getTagsString());
    }
}
