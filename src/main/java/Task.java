public abstract class Task {
    protected String description;
    protected boolean isCompleted;
    Task(String description) throws EmptyArgumentException {
        if(description.isEmpty()) throw new EmptyArgumentException();
        this.description = description;
    }
    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + description;
    }
}
