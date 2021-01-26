public class Event extends Task{
    private String date;

    public Event(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String toSaveString(){
        return "E," + super.toSaveString() + "," + this.date;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(at: " + this.date + ")";
    }
}
