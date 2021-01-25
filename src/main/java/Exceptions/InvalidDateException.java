package Exceptions;

public class InvalidDateException extends DukeException{

    public InvalidDateException(String invalidDate) {
        super(invalidDate + " is not a valid date. Please try again :(");
    }
}
