package duke.task;

public class Deadline extends Task{
    private final String deadline;

    /**
     * duke.task.Deadline constructor.
     *
     * @param content duke.task.Task description
     * @param deadline The deadline task has to be completed by.
     */
    public Deadline(String content,String deadline){
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString()+ String.format(" (by:%s)",this.parseDate(this.deadline.strip()));
    }

    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        return "D|"+ done + "|" + this.getDesc()+ "|" + this.deadline;
    }
}
