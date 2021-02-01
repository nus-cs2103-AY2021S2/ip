package ip.src.main.java;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Event extends Task{
    protected Date at;

    public Event(String content,String at_str) {
        super(content);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date at = sourceFormat.parse(at_str);
            this.at = at;
        } catch (ParseException e) {
            System.out.println("Date and Time is not in correct format DD/MM/YYYY HH:MM");
            e.printStackTrace();
        }

    }

        @Override
    public String toString() {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strDate = sourceFormat.format(this.at);
            if(!this.done){
            return "E | 0 | " + super.toString() + " | " + strDate;
        }else {
            return "E | 1 | " + super.toString() + " | " + strDate;
        }
    }
}
