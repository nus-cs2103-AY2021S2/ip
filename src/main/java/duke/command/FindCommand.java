package duke.command;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a {@code FindCommand} object with a keyword component.
     * @param keyword
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
