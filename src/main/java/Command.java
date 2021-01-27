import java.util.ArrayList;

public class Command {
    public CommandType type;
    public ArrayList<String> args;

    public Command(CommandType t) {
        type = t;
        args = new ArrayList<>();
    }

    public Command(CommandType t, String xs) {
        type = t;
        args = new ArrayList<>();
        args.add(xs);
    }

    public Command(CommandType t, String xs, String ys) {
        type = t;
        args = new ArrayList<>();
        args.add(xs);
        args.add(ys);
    }
}
