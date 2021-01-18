public class Event extends Task{
    protected final String eventTime;

    public Event(String description, String eventTime) {
        super(description, "[E]");
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String[] str = eventTime.split(" ");
        String date = "";
        for(int i = 1; i < str.length; i++) {
            date = date.concat(str[i]);
            if(i != str.length-1) {
                date = date + " ";
            }
        }
        return super.toString() + " (at: " + date + ")";
    }
}
