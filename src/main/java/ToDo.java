public class ToDo extends Task {
    ToDo (String Description) {
        super(Description);
    }

    ToDo (String[] creationCommand) {
        super(Integer.parseInt(creationCommand[1]), creationCommand[2]);
    }

    @Override
    public String TaskInformation () {
        return "[T]" + super.TaskInformation();
    }

    @Override
    public String creationCommand () {
        return "T :: " + super.creationCommand();
    }
}