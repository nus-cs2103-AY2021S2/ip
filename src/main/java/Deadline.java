public class Deadline extends Task{
    private String deadline;

    public Deadline(String content,String deadline){
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString()+ String.format(" (by:%s)",this.deadline);
    }
    @Override
    public String toFileString() {
        String done;
        if (this.getDone()) {
            done = "1";
        } else {
            done = "0";
        }
        String string = "D|"+ done + "|" + this.getDesc()+ "|" + this.deadline;
        return string;
    }
}
