public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskName() {
        return "[T]" + super.getTaskName();
    }



}
