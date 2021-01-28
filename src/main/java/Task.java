public abstract class Task {
    private final String description;
    protected boolean isDone;

    public Task(String description) throws EmptyArgument {
        description = description.trim();
        if (description.isEmpty()){
            throw new EmptyArgument();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "*" : " "); //Don't use unicode, cause it can't test properly
    }

    @Override
    public String toString() {
        return "["+ this.getStatusIcon()+"]: " +
                description;
    }

    abstract String toFileString();

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    String toBaseFileString(){
        return (isDone ? "1" : "0") + "," + description.length() + "," + description;
    }
}