import java.util.Scanner;

/**
 * Class that handles the command to exit the programme.
 */

public class ByeCommand extends Command{
    private Scanner scanner;
    public ByeCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    public Result execute() {
        this.scanner.close();
        Result result = new Result("Bye. See you again!");
        return result;
    }
}
