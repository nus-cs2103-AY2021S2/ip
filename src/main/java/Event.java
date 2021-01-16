public class Event extends Task {
    public String dateTimeRange;

    public Event(String desc, String dateTimeRange) {
        super(desc);
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
}
