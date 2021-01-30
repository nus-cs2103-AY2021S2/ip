package sharadhr.duke.exception;

/**
 * DukeInvalidDateException
 */
public class DukeInvalidDateException extends DukeException
{
    private static final long serialVersionUID = 1967070878565904626L;

    private String dateString;
    
    public DukeInvalidDateException(String message, String thrownBy, String dateString)
    {
        super("Invalid date format: ", thrownBy);
        this.dateString = dateString;
    }

    @Override
    public String toString()
    {
        return String.format("Message:%s%s%nThrown by:%s%nStack trace:%s", this.getMessage(), 
                this.dateString,
                this.thrownBy,
                getStackTrace());
    }
}