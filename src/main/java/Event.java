public class Event extends Task{
    protected String time;

    Event(int id, String description, String time){
        super(id, description);
        this.time = time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
