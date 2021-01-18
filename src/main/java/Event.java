public class Event extends Task{
    private final String time;

    Event(String description, String time){
        super(description);
        this.time = time;
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (%s)", getStatusIcon(), description, time);
    }
}
