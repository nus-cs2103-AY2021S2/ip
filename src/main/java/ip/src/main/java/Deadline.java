package ip.src.main.java;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Deadline extends Task{
    protected Date by;

    public Deadline(String content,String by_str) {
        super(content);
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date by = sourceFormat.parse(by_str);
            this.by = by;
        } catch (ParseException e) {
            System.out.println("Date and Time is not in correct format DD/MM/YYYY HH:MM");
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strDate = sourceFormat.format(this.by);
        if(!this.done){
            return "D | 0 | " + super.toString() + " | " + strDate;
        }else {
            return "D | 1 | " + super.toString() + " | " + strDate;

        }
    }
}
