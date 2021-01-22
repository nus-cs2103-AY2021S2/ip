public class Event extends Task {
    public String dateTimeRange;

    public Event(String desc, String dateTimeRange) {
        super(desc, false);
        this.dateTimeRange = dateTimeRange;
    }

    public Event(String desc, String dateTimeRange, boolean isDone) {
        super(desc, isDone);
        this.dateTimeRange = dateTimeRange;
    }

    @Override
    public String getDesc() {
        return this.desc + " (at: " + this.dateTimeRange + ")";
    }

    @Override
    public String getTypeSymbol() {
        return "E";
    }

    @Override
    public String toSaveInfoString() {
        return super.toSaveInfoString() + " | " + this.dateTimeRange;
    }
}
