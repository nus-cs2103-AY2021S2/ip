public class Event extends Task{
    private String comments;

    public Event(String taskName, String comments) {
        super(taskName);
        this.comments = comments;
    }


    @Override
    public String toString() {
        return String.format("[E][%s] %d. %s ( %s )", super.isDone(), super.getIndex(),
                super.getTaskName(), this.comments);
    }
}
