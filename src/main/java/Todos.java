class Todos extends Task {
    public Todos(String description) throws EmptyArgumentException {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
