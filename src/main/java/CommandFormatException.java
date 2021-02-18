public class CommandFormatException extends Exception {
    public CommandFormatException() {
        super("The command you have entered did not follow the format. Please try again!");
    }
}
