public class Task {
    private final String name;
    private final boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }


    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }
}
