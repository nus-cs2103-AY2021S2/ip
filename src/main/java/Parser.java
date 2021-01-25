import java.util.Arrays;

public class Parser {
    public static Command parse(String input) {
        String[] words = input.trim().split(" ");
        String cmdStr = words[0];

        // Recombine cmdArgs for further parsing in individual cmd classes
        String[] remain = Arrays.copyOfRange(words, 1, words.length);
        String cmdArgs = String.join(" ", remain);

        Command cmd;

        switch (cmdStr) {
        case "bye":
            cmd = new ByeCmd();
            break;
        case "list":
            cmd = new ListCmd();
            break;
        case "done":
            cmd = new DoneCmd(cmdArgs);
            break;
        case "todo":
        case "deadline":
        case "event":
            cmd = new AddCmd(cmdArgs, TaskType.valueOf(cmdStr.toUpperCase()));
            break;
        case "delete":
            cmd = new DeleteCmd(cmdArgs);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that command means :-(");
        }

        return cmd;
    }
}
