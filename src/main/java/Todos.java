class Todos extends Task {
    public Todos(String description) throws EmptyArgumentException {
        super(description);
    }
    public String getFileString() {
        StringBuilder res = new StringBuilder();
        res.append("T |");
        if(isCompleted) res.append(" 1 | ");
        else res.append(" 0 | ");
        res.append(super.description);
        return res.toString();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
