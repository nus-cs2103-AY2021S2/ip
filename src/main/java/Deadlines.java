public class Deadlines extends Task{

    private String comments;

    public Deadlines(String taskName, String comments) {
        super(taskName);
        this.comments = comments;
    }


    @Override
    public String toString() {
        return String.format("[D][%s] %d. %s ( %s )", super.isDone(), super.getIndex(),
                super.getTaskName(), this.comments);
    }
}
