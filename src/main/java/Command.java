public class Command {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String TAG = "tag";

    protected String instruction;
    protected String args;
    protected int index;

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
        case TAG:
            String[] parts = args.split(" ", 2);
            try {
                this.instruction = instruction;
                this.index = Integer.parseInt(parts[0]) - 1;
                this.args = parts.length > 1 ? parts[1] : "";
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }
            break;
        case FIND:
        case TODO:
        case EVENT:
        case DEADLINE:
            this.instruction = instruction;
            this.args = args;
            this.index = -1;
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

    public int getIndex() {
        return this.index;
    }
}
