import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Duke is a CLI chat-bot that handles task tracking.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class DukeBot {
    private Scanner scanner;
    private TaskManager taskManager;

    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.taskManager = new TaskManager();
    }

    /**
     * Activates the chat-bot so that it keeps taking inputs from the user via System.in
     * until the "bye" input is given.
     *
     * The run method ensures that the user has provided a valid command, otherwise it
     * prints out a warning message.
     */
    public void run() {
        while (taskManager.isActive()) {
            try {
                String input = scanner.nextLine();
                String[] inputArr = input.split("\\s", 2);
                String commandStr = inputArr[0];
                String description = inputArr.length == 2 ? inputArr[1] : "";
                Command command = Command.get(commandStr);
                taskManager.handleCommand(command, description);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        this.scanner.close();
    }
}

