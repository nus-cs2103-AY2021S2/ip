import java.util.ArrayList;

/** Describes what a command does based on its CommandType. */
public class Command {
    public CommandType type;
    public ArrayList<String> args;

    /**
     * Returns a Command object that takes no arguments.
     *
     * @param t The command type
     */
    public Command(CommandType t) {
        type = t;
        args = new ArrayList<>();
    }

    /**
     * Returns a Command object that takes an argument.
     *
     * @param t The command type
     * @param xs The only argument that the command takes
     */
    public Command(CommandType t, String xs) {
        type = t;
        args = new ArrayList<>();
        args.add(xs);
    }

    /**
     * Returns a Command object that takes two arguments.
     *
     * @param t The command type
     * @param xs The first argument that the command takes
     * @param ys The second argument that the command takes
     */
    public Command(CommandType t, String xs, String ys) {
        type = t;
        args = new ArrayList<>();
        args.add(xs);
        args.add(ys);
    }
}
