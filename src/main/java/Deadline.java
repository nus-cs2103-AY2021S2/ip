class Deadline extends Task {
    public Deadline(String description) throws EmptyArgumentException {
        super(description);
    }
    public String getFileString() {
        StringBuilder res = new StringBuilder();
        res.append("D |");
        if(isCompleted) res.append(" 1 | ");
        else res.append(" 0 | ");
        res.append(super.description);
        return res.toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}