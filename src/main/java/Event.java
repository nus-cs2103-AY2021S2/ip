public class Event extends Task {
    private String eventTime;

    public Event(String desc, String eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    public static Event newInstance(String args) {
        String[] argArr = args.split("/");
        for (String s: argArr) {
            if (s.startsWith("at ")) {
                return new Event(argArr[0], s.substring(3));
            }
        }
        return new Event(argArr[0], "N/A");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }
}
