public class ToDo extends Task {
    private String name;
    private boolean done;

    ToDo(String name) {
        this.name = name;
        this.done = false;
    }

    ToDo(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    @Override
    Task done() {
        return new ToDo(this.name, true);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        }
        return "[T][ ] " + this.name;
    }
}
