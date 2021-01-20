public class Deadline extends Task {

    private String end;

    public Deadline (String name, String end) {
        super(name);
        this.end = end;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getEnd());
    }

}
