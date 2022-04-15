package rick.exceptions;

/**
 * Custom exception class
 */
public class RickException extends Exception {
    @Override
    public String getMessage() {
        return "Rick's sorry, but Rick don't know what that means.\n"
                + "Type \"help\" to view the list of available commands.";
    }
}