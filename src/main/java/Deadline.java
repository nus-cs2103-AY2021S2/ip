class Deadline extends Task {
    public Deadline(String description) throws EmptyArgumentException {
        super(description);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}