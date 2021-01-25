public class Deadline extends Task {
    private final String time;

    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    String getTime() {
        return this.time;
    }

    String getSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.getTime());
    }
}
