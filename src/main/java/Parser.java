import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private final Scanner in;
    private final PrintStream out;
    /** A boolean function to check if the user decides to terminate the program. */
    public boolean canContinue = true;

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
                case "bye" -> byeCommand(ui);
                default -> wrongCommand();
            }
        } catch (DukeException e) {
            out.println(e);
        }
    }

    /**
     * Signal termination of the program.
     */
    public void terminate() {
        this.canContinue = false;
    }

    /**
     * Tells the user that the input given is invalid.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public void wrongCommand() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Saying bye to the user when the user decides to quit.
     */
    public void byeCommand(Ui ui) {
        ui.byeMsg();
        terminate();
    }
}
