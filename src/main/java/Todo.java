public class Todo extends Task {
    public Todo(String n) {
        isDone = false;
        name = n;
    }

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]")
             + " Todo: "
             + name;
    }
}
