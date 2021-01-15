public class NormalTask extends Task {
    public NormalTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (done) {
            return "[ ][X] " + name;
        }
        return "[ ][ ] " + name;
    }
}
