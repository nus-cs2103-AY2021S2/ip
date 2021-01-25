public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    public static Todo deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = words[1].equals("1");
        String content = words[2];

        Todo todo = new Todo(content);
        if (isDone) {
            todo.markDone();
        }

        return todo;
    }

    @Override
    public String getSerialized() {
        return String.format("T | %s | %s", getSerializedIsDone(), getContent());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getContent());
    }
}
