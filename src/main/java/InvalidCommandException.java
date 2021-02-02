public class InvalidCommandException extends Exception {
    private String badCommand;
    public InvalidCommandException(String badCommand) {
        this.badCommand = badCommand;
    }
    @Override
    public String getMessage() {
        return "Command " + badCommand + " is not recognized";
    }
}
