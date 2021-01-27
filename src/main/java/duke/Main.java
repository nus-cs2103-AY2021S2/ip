package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import duke.duke.Duke;

import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.tasks.TaskList;

import duke.ui.Ui;

import java.io.File;
import java.io.IOException;

public class Main {
    //private final static File f = new File("src/main/data/duke.txt");
    private final static File file = new File("duke.txt");

    public static void main(String[] args) throws IOException {
        runMain();
    }

    public static Duke start() {
        Duke bot = null;
        try {
            if (!(file.createNewFile())) {
                TaskList previous = Storage.runFile(file);
                bot = new Duke(previous);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bot == null) {
            bot = new Duke();
        }

        Ui.showWelcomeMessage(bot);
        return bot;
    }

    public static void runUserCommand(Command userCommand, Duke bot) throws IOException {
        if (userCommand instanceof ListCommand) {
            bot.showTasks();
        } else if (userCommand instanceof DoneCommand) {
            bot.markAsDone(((DoneCommand) userCommand).getTaskNumber());
            Storage.saveFile(file, bot);
        } else if (userCommand instanceof TodoCommand) {
            bot.addTask(((TodoCommand) userCommand).getDescription(),
                    userCommand.getCommand(), null);
            Storage.saveFile(file, bot);
        } else if (userCommand instanceof DeadlineCommand) {
            bot.addTask(((DeadlineCommand) userCommand).getDescription(), userCommand.getCommand(),
                    ((DeadlineCommand) userCommand).getDeadline());
            Storage.saveFile(file, bot);
        } else if (userCommand instanceof EventCommand) {
            bot.addTask(((EventCommand) userCommand).getDescription(), userCommand.getCommand(),
                    ((EventCommand) userCommand).getEventTime());
            Storage.saveFile(file, bot);

        } else if (userCommand instanceof DeleteCommand) {
            bot.removeTask(((DeleteCommand) userCommand).getTaskNumber());
            Storage.saveFile(file, bot);
        }

    }

    public static void runMain() throws IOException {
        Duke bot = start();
        int count = 0;

        while (true) {
            Command userCommand;
            if (count > 0) {
                Ui.askForUserRequest();
            }
            String string = Ui.readUserInput();
            try {
                userCommand = Parser.processInput(string, bot);
            } catch (InvalidCommandException | InvalidArgumentException ex) {
                Ui.print(ex.getMessage());
                continue;
            }

            if (!(userCommand instanceof ByeCommand)) {
                runUserCommand(userCommand, bot);
            } else {
                exit();
                break;
            }

            count++;
            Ui.printEmptyLine();
        }
    }

    public static void exit() {
        Ui.showExitMessage();
    }
}
