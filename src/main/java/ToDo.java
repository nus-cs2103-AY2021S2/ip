public class ToDo extends Task {
    ToDo (String Description) {
        super(Description);
    }

    @Override
    public String TaskInformation () {
        return "[T]" + super.TaskInformation();
    }
}