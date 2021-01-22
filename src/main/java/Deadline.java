public class Deadline extends Task {
    public String dateTime;

    public Deadline(String desc, String dateTime) {
        super(desc, false);
        this.dateTime = dateTime;
    }

    public Deadline(String desc, String dateTime, boolean isDone) {
        super(desc, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String getDesc() {
        return this.desc + " (by: " + this.dateTime + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "D";
    }

    @Override
    public String toSaveInfoString() {
        return this.getTypeSymbol() + " | " + (this.isDone ? "1" : "0") + " | " + this.desc + " | " + this.dateTime;
    }
}
