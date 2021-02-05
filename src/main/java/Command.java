public abstract class Command {
    protected String line;
    int itemNo = -1;
    public Command(String line) {
        this.line = line;
    }

    public Command() {

    }

    public abstract boolean isExit();
    public abstract String getType();

    public abstract String getDescription();
}
