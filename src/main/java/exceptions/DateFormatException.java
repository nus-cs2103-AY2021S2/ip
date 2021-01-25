package main.java.exceptions;

public class DateFormatException extends Exception {

    public DateFormatException() {
        super("OOPS!!! Please give your date in the format YYYY-MM-DD.");
    }

}
