public class Command {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";

    protected String instruction;
    protected String args;

    /**
     * Constructor for the Bye and List commands.
     *
     * @param instruction String entered by user.
     */
    public Command(String instruction) {
        switch (instruction) {
        case BYE:
        case LIST:
            this.instruction = instruction;
            break;
        case TODO:
        case EVENT:
        case DEADLINE:
            throw new EmptyDescriptionException(instruction);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Constructor for the remaining commands.
     *
     * @param instruction First word entered by user.
     * @param args Subsequent words entered by user.
     */
    public Command(String instruction, String args) {
        switch (instruction) {
        case DONE:
        case DELETE:
        case FIND:
        case TODO:
        case EVENT:
        case DEADLINE:
            this.instruction = instruction;
            this.args = args;
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Returns the command's instruction
     *
     * @return instruction
     */
    public String getInstruction() {
        return this.instruction;
    }

    /**
     * Return the command's arguments
     *
     * @return arguments
     */
    public String getArgs() {
        return this.args;
    }
}
