package sharadhr.duke.exception;

/**
 * Thrown 
 */
public class DukeInvalidCommandException extends DukeException
{
    private static final long serialVersionUID = 5862830938541195307L;

    private String command;

    public DukeInvalidCommandException(String command, String thrownBy)
    {
        super("Invalid command: " + command, thrownBy);
    }

    @Override
    public String toString()
    {
        return String.format("Message:%s%s%nThrown by:%s%nStack trace:%s", this.getMessage(),
                this.command,
                this.thrownBy, 
                getStackTrace());
    }
}