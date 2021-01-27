package duke.exception;

/** An exception that happened due to chat bot trying to execute a command */
public class DukeCommandException extends DukeException{
    /** Type of command that throws the exception */
    private String commandType;
    /** Parameters that the command was using */
    private String params;

    /**
     * Constructor for DukeCommandException
     *
     * @param commandType Type of the command
     * @param params Parameters processed by the command
     * @param msg Error message that can be recalled
     */
    public DukeCommandException(String commandType, String params, String msg) {
        super(msg);
    }

    /**
     * Returns the type of the command
     *
     * @return Type of the command
     */
    public String getCommandType() {
        return this.commandType;
    }

    /**
     * Returns a string containing the parameters of the command
     *
     * @return Parameters of the command
     */
    public String getParams() {
        return this.params;
    }
}
