public class Event extends Task{
    protected String at;

    public Event(String content,String at){
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        if(!this.done){
            return "[E][ ] " + super.toString() + " (at: " + at + ")";
        }else {
            return "[E][X] " + super.toString() + " (at: " + at + ")";
        }
    }
}
