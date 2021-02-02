abstract class Task {
    protected boolean isCompleted = false;

    protected final String description;

    public Task(String descr) throws EmptyArgumentException {
          if(descr.isEmpty()) throw new EmptyArgumentException();
          description = descr;
    }
    public abstract String getFileString();

    private char getStatusIcon() { return isCompleted ? 'X' : ' '; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" +  description;
    }
}
