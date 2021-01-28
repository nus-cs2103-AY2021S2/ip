import java.time.DateTimeException;
import java.time.LocalDate;

//tasks that start at a specific time and ends at a specific time
public class Event extends Task{
    private String eventDateString;
    private LocalDate eventDate;
    Event(String eventDateString, String eventDetail){
        super(eventDetail);
        try{
            this.eventDate = Task.dateStringToDate(eventDateString);
            this.eventDateString = eventDate.getMonth().toString().toLowerCase() + " " +
                    eventDate.getDayOfMonth() + " " + eventDate.getYear();
        }
        catch(DateTimeException e){
            System.err.println("Please input date in yyyy-MM-dd format.");
        }
    }
    public String getEventDateString(){
        return this.eventDateString;
    }

    public LocalDate getEventDate(){
        return this.eventDate;
    }

    @Override
    public String toString(){
        return "[E] | " + super.toString() + " | at: " + this.eventDateString;
    }
}
