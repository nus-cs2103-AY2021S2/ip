public class Event extends Task {
    private String At;

    Event (String Description, String At) {
        super(Description);
        this.At = At;
    }

    @Override
    public String TaskInformation () {
        return "[E]" + super.TaskInformation() + " (at: " + this.At + ")";
    }
}