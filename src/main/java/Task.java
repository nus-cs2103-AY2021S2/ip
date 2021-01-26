public abstract class Task {
    private String description;
    private boolean isDone;
    protected static final char saveDelimiter = '|';

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return blank or tick symbol
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    protected String toSaveFormatPrefix() {
        return String.valueOf(typeSymbol()) + saveDelimiter + (isDone ? "1" : "0")
                + saveDelimiter + description;
    }

    public abstract String toSaveFormat();

    public abstract char typeSymbol();
}
