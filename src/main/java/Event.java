public class Event extends Task {
    private String At;

    Event (String Description, String At) {
        super(Description);
        this.At = At;
    }

    Event (String[] creationCommand) {
        super(Integer.parseInt(creationCommand[1]), creationCommand[2]);
        this.At = creationCommand[3];
    }

    @Override
    public String TaskInformation () {
        return "[E]" + super.TaskInformation() + " (at: " + this.At + ")";
    }

    @Override
    public String creationCommand () {
        return "E :: " + super.creationCommand() + " :: " + this.At;
    }
}