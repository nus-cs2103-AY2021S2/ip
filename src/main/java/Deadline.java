//Deadlines: tasks that need to be done before a specific date/time
public class Deadline extends Task{
    private String deadline;
    Deadline(String deadline, String deadlineDetail){
        super(deadlineDetail);
        this.deadline = deadline;
    }
    public String getDeadline(){
        return this.deadline;
    }

    @Override
    public String toString(){
        return "[D]" + " | " + super.toString() + "| (by: " + this.deadline + ")";
    }
}
