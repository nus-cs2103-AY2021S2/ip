package duke.common;

import java.util.Scanner;

import duke.command.Command;
import duke.interaction.Parser;
import duke.task.TaskList;

public class DukeBot {
    private final Scanner scanner;
    private final Parser parser;
    private final TaskList taskList;

    public DukeBot(final Scanner sc) {
        this.scanner = sc;
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    public void printOut(final String msg) {
        System.out.println(DukeString.SEPARATOR);
        System.out.println(msg);
        System.out.println(DukeString.SEPARATOR);
    }

    public void run() {
        Command command;
        printOut(DukeString.MESSAGE_WELCOME);

        while (!parser.isBye()) {
            try {
                command = parser.parseInput(scanner.nextLine());
                printOut(command.execute(taskList));

            } catch (DukeException.InvalidCommand
                    | DukeException.InvalidTask
                    | DukeException.EmptyDescription
                    | DukeException.EmptyEventDate
                    | DukeException.InvalidEventEnd e) {
                printOut(e.getMessage());
            }

        }
    }
}
