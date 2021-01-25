public class Deadline extends Task {
    private String By;

    Deadline (String Description, String By) {
        super(Description);
        this.By = By;
    }

    Deadline (String[] creationCommand) {
        super(Integer.parseInt(creationCommand[1]), creationCommand[2]);
        this.By = creationCommand[3];
    }

    @Override
    public String TaskInformation () {
        return "[D]" + super.TaskInformation() + " (by: " + this.By + ")";
    }

    @Override
    public String creationCommand () {
        return "D :: " + super.creationCommand() + " :: " + this.By;
    }
}
