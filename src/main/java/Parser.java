import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private final Scanner in;
    private final PrintStream out;

    public Parser() {
        this(System.in, System.out);
    }

    public Parser(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void processInput(ArrayList<Task> tasks, int totalTasks, Ui ui) {
        String nextInput = in.nextLine();
        String command = nextInput.contains(" ") ? nextInput.split(" ")[0] : nextInput;
        try {
            switch (command) {
                case "todo" -> Duke.todo(nextInput, tasks);
                case "deadline" -> Duke.deadline(nextInput, tasks);
                case "event" -> Duke.event(nextInput, tasks);
                case "done" -> Duke.done(nextInput, tasks, totalTasks);
                case "delete" -> Duke.delete(nextInput, tasks, totalTasks);
                case "list" -> Duke.list(tasks, totalTasks);
                case "bye" -> Duke.bye(ui.username);
                default -> Duke.wrongCommand();
            }
        } catch (DukeException e) {
            out.println(e);
        }
    }
}
