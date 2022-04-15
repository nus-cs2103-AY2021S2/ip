public class InvalidTaskException extends IllegalArgumentException {
    public InvalidTaskException(String instruction) {
        super("☹ You have provided an invalid " + instruction + ".");
    }
}
