package duke.command;

import java.io.File;

import duke.duke.Duke;

public class FindCommand extends Command {
    private static final String usageMessage = "Command: find <keyword>\n"
            + "Description: Searches for a keyword\n";
    private final String keyword;

    /**
     * Creates a {@code FindCommand} object with a keyword component.
     * @param keyword Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public static String getUsageMessage() {
        return usageMessage;
    }

    @Override
    public String run(File file, Duke bot) {
        return bot.showTasksContainingKeyword(getKeyword());
    }
}
