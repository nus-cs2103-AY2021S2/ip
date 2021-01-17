public class Deadline extends Task{
    protected String deadline;

    Deadline(int id, String description, String deadline){
        super(id, description);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
