package duke.task;

public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    public static Todo deserialize(String str) {
        String[] words = str.split(" \\| ");
        boolean isDone = Boolean.parseBoolean(words[1]);
        String content = words[2];

        Todo todo = new Todo(content);
        if (isDone) {
            todo.markDone();
        }

        return todo;
    }

    /**
     * Returns true if the task contains str in one of its fields
     *
     * @param str the target string
     * @return true if the task contains str in one of its fields
     */
    @Override
    public boolean hasStrInProps(String str) {
        return getContent().contains(str);
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
