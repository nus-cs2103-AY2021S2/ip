public class ToDos extends Task{
    protected String taskName;

    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String toSave() {
        return "T / " + super.isDoneString + super.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
