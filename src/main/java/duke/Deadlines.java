package duke;

public class Deadlines extends Task {

    private String dueDate;

    public Deadlines(String title, String dueBy) {
        super(title);
        this.dueDate = dueBy;
    }

    public Deadlines(String title, Boolean b, String dueBy) {
        super(title, b);
        this.dueDate = dueBy;
    }

    @Override
    public String changeFormat(){
        return "D" + super.changeFormat() + ":" + this.dueDate;
    }

    @Override
    public String toString() {
        return  "[D]" + super.toString() +  "(by:" + dueDate + ")";
    }
}
