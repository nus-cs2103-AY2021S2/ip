package chat;

import chat.command.Command; 
import chat.command.ListCommand; 
import chat.command.DoneCommand;
import chat.command.DeleteCommand;
import chat.command.AddCommand;
import chat.command.ExitCommand;

public class Parser {
    //deals with making sense of user command
    
    //parse command
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
        } else {
            throw new ChatException("Sorry this instruction does not exist!\n" +
                    "Please choose from the following: " +
                    "todo, deadline, event, done, list, bye");
        }
    }
    
}
