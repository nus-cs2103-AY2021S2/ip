public class Deadline extends Task {
    private String By;

    Deadline (String Description, String By) {
        super(Description);
        this.By = By;
  }

    @Override
    public String TaskInformation () {
        return "[D]" + super.TaskInformation() + " (by: " + this.By + ")";
    }
}
