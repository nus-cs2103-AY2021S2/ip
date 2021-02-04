package chat;

import chat.command.Command;
import chat.command.ListCommand;
import chat.command.DoneCommand;
import chat.command.AddCommand;
import chat.command.DeleteCommand;
import chat.command.ExitCommand;
import chat.command.FindCommand;

/**
 * Parser class deals with making sense of user command.
 */

public class Parser {

    /**
     * Parses command to their respective commands.
     * 
     * @param str Inputted command string from user to Chat the Cat.
     * @return Command object.
     * @throws ChatException If command is not known by Chat the Cat.
     */
    public static Command parse(String str) throws ChatException {
        if (str.equals("list")) {
            return new ListCommand();
        } else if (str.startsWith("done")) {
            return new DoneCommand(str);
        } else if (str.startsWith("todo") | str.startsWith("deadline") | str.startsWith("event")) {
            return new AddCommand(str);
        } else if (str.startsWith("delete")) {
            return new DeleteCommand(str);
        } else if (str.equals("bye")) {
            return new ExitCommand();
        } else if (str.startsWith("find")) {
            return new FindCommand(str);
        } else {
            throw new ChatException("Sorry this instruction does not exist!\n" +
                    "Please choose from the following: " +
                    "todo, deadline, event, done, list, bye");
        }
    }
    
}
