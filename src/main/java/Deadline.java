public class Deadline extends Task{
    protected String by;
    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskInfo() {
        return "[D]" + super.getTaskInfo() + " (by: " + by + ")";
    }

    @Override
    public String getTaskInfoOfFile(){
        return "D | "+(super.isDone?"1":"0")+" | "+super.getDescription() +" | "+this.by;
    }
}
