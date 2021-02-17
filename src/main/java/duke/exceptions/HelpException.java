package duke.exceptions;

/**
 * Represents the exception from the help function
 * when the function receives an invalid input or command.
 */
public class HelpException extends DukeException{
    public HelpException(){
        super("HelpException");
    }

    @Override
    public String toString(){
        return "Master, please key in a valid command after the \"help\" keyword.\n\n" +
                "Alternatively, enter \"help\" to get the full list of commands.\n";
    }
}