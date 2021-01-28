public class FindCommand extends Command {
    private final String searchString;

    private FindCommand(String searchString, Duke duke) {
        super(-1, duke);
        this.searchString = searchString;
    }

    public static FindCommand getFindCommand(String searchString) {
        return new FindCommand(searchString, null);
    }


}