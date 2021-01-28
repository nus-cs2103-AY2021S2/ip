package soonkeatneo.duke;

public class InvalidTaskException extends IllegalArgumentException {
    public InvalidTaskException() {
        super("    Hmm... Looks like the task number isn't correct. Check it and try again!");
    }
}
