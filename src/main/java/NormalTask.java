public class NormalTask extends Task {
    public NormalTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (done == Status.DONE) {
            return "[ ][X] " + name;
        }
        return "[ ][ ] " + name;
    }
}
