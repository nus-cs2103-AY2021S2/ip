package duke.exception;

public class DukeCommandException extends DukeException{
    /**
     * Type of command that throws the exception
     */
    private String commandType;
    /**
     * Parameters that the command was processing
     */
    private String params;

    /**
     * Constructor for DukeCommandException
     * @param commandType Type of command
     * @param params Parameters processed by that command
     * @param msg Message to display for this exception
     */
    public DukeCommandException(String commandType, String params, String msg) {
        super(msg);
    }

    /**
     * Return the type of command
     * @return
     */
    public String getCommandType() {
        return this.commandType;
    }

    /**
     * Return the parameters of the command
     * @return
     */
    public String getParams() {
        return this.params;
    }
}
