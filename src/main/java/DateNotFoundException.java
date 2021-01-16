public class DateNotFoundException extends Exception {
    public DateNotFoundException() {
        super("Please enter a valid deadline (after \"/by\") for Deadline Tasks"
                + "or time (after \"/at\") for Event Tasks.");
    }
}
