package ip.src.main.java;

import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Deadline is a type of Task with attributes String content and a Date stored as a Date object.
 *
 */

public class Deadline extends Task{
    protected Date by;

    /**
     * Constructor for Deadline Class.
     *
     * @param content String content given by user.
     * @param by_str Deadline date given by user, parsed into a Date object with the format dd/MM/yyyy HH:mm.
     */

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

    /**
     * toString() of Deadline Class.
     *
     * @return toString() representation of a Deadline object with its done status, content and date.
     */

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
