package rick;

public class InvalidDescriptionException extends RickException {
    @Override
    public String getMessage() {
        return "The description cannot be empty!\n"
                + "Type \"help\" to view the list of available commands.";
    }
}
