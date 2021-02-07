package duke.exceptions;

public class DeadLineException extends DukeException {
    public DeadLineException(String error) {
       super("Sorry Master, please key in the deadline task in the following format:\n"
             + "\' Deadline task name /at date(in YYYY-MM-DD HH:MM format)\'.");
    }
}
