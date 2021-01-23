package duke.task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, false);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String getTypeSymbol() {
        return "T";
    }

    @Override
    public String toSaveInfoString() {
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc;
    }
}
