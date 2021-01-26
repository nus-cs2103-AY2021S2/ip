package duke;

import duke.commands.Command;
import duke.tasks.TaskManager;
import duke.exceptions.DukeException;

import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * duke.DukeBot is the controller class for the CLI chat-bot duke.Duke.
 * <p>
 * duke.DukeBot parses user input Strings for valid commands and thus only
 * allows valid commands to be sent to the duke.tasks.TaskManager.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class DukeBot {
    private Scanner scanner;
    private TaskManager taskManager;

    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        Path pathToData = Paths.get("data/duke.txt");
        this.taskManager = new TaskManager(pathToData);
    }

    /**
     * Activates the chat-bot so that it keeps taking inputs from the user via System.in
     * until the "bye" input is given.
     * <p>
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

