public class Event extends Task{
    protected String at;
    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskInfo() {
        return "[E]" + super.getTaskInfo() + " (at: " + at + ")";
    }

    @Override
    public String getTaskInfoOfFile(){
        return "E | "+(super.isDone?"1":"0")+" | "+super.getDescription() +" | "+this.at;
    }
}
