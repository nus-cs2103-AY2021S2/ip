package duke.command;

public class FindCommand extends Command{
    public String keyword;

    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
