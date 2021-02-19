package rick.exceptions;

public class MissingFlagException extends RickException {
    @Override
    public String getMessage() {
        return "Invalid Flag!\n"
                + "Type \"help\" to view the list of available commands.";
    }
}
