public class Command {
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String TODO = "todo";
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";

    protected String instruction;
    protected String args;

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

    public Command(String instruction, String args) {
        switch (instruction) {
        case DONE:
        case DELETE:
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

    public String getInstruction() {
        return this.instruction;
    }

    public String getArgs() {
        return this.args;
    }
}
