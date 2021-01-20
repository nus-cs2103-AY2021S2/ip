public class Todo {
    private String name;
    private boolean done;

    public Todo(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return name;
    }
}
