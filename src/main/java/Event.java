class Event extends Task {
    public Event(String description) throws EmptyArgumentException {
        super(description);
    }

    @Override
    public String getFileString() {
        StringBuilder res = new StringBuilder();
        res.append("E |");
        if(isCompleted) res.append(" 1 | ");
        else res.append(" 0 | ");
        res.append(super.description);
        return res.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
