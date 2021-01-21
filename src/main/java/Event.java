public class Event extends Task{
    protected String afterAt;
    public Event (String info, String afterAt) {
        super(info);
        this.afterAt = afterAt;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at:" + this.afterAt + ")";
    }
}
