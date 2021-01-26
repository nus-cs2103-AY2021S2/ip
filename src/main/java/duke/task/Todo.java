package duke.task;

public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    private Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    public static Todo deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];

        return new Todo(content, isDone);
    }

    @Override
    public String getSerialized() {
        return String.format("T | %s | %s", getIsDone(), getContent());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getContent());
    }
}
