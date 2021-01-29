package surrealchat.help;

import surrealchat.command.DeadlineCommand;
import surrealchat.command.DeleteCommand;
import surrealchat.command.DoneCommand;
import surrealchat.command.EditCommand;
import surrealchat.command.EventCommand;
import surrealchat.command.FindCommand;
import surrealchat.command.ListCommand;
import surrealchat.command.ToDoCommand;
import surrealchat.easteregg.OrangEasterEgg;
import surrealchat.easteregg.VegetalEasterEgg;

/**
 * Class for displaying descriptions to help user in understanding commands.
 */
public class HelpMode {
    /**
     * Displays descriptions on how to use commands.
     * @param command The command which user wishes to understand how to use.
     * @return String related to how the above command works.
     */
    public static String displayHelp(String command) {
        switch(command.trim()) {
        case "bye":
            return "Quits the program.\n";
        case "todo":
            return ToDoCommand.displayHelp();
        case "deadline":
            return DeadlineCommand.displayHelp();
        case "event":
            return EventCommand.displayHelp();
        case "delete":
            return DeleteCommand.displayHelp();
        case "done":
            return DoneCommand.displayHelp();
        case "find":
            return FindCommand.displayHelp();
        case "list":
            return ListCommand.displayHelp();
        case "edit":
            return EditCommand.displayHelp();
        case "orang":
            return OrangEasterEgg.displayHelp();
        case "vegetal":
            return VegetalEasterEgg.displayHelp();
        default:
            throw new IllegalArgumentException("Helper does not recognise command. Not Stonks!");
        }
    }
}
