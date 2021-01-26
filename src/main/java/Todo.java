public class Todo extends Task {
    public Todo(String n) {
        isDone = false;
        name = n;
    }

    public String toText() {
        String d = isDone ? "+" : "-";
        return String.format("T | %1$s | %2$s", d, name);
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]")
             + " Todo: "
             + name;
    }
}
