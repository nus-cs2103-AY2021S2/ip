
//Deadline class with Date
public class Deadlines extends Task{
    protected String deadline;
    public Deadlines(String name, String deadline) {

        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }
    @Override
    public String toString() {
        return "[D]"+super.toString()+" (by: "+deadline+")";
    }
}
