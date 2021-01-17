public class Deadlines extends Task {

    private String dueDate;

    public Deadlines(String title, String dueBy) {
        super(title);
        this.dueDate = dueBy;
    }

    @Override
    public String toString() {
        return  "[D]" + super.toString() +  "(by:" + dueDate + ")";
    }
}
