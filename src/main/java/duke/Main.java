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

/**
 * Main entry point to the chat bot application.
 * Initialises application and starts user interaction.
 */

public class Main {
    //private final static File f = new File("src/main/data/duke.txt");
    private final static File f = new File("duke.txt");

    /**
     * Entry point of chat bot application.
     * @param args Command-line arguments passed into application
     * @throws IOException if error occurs while writing to the file
     */
    public static void main(String[] args) throws IOException{
        runMain();
    }

    /**
     * Initializes task list from storage and prints welcome greeting.
     * @return a Duke object that manages task list operations
     */
    public static Duke start() {
        Duke bot = null;
        try {
            if (!(f.createNewFile())){
                TaskList previous = Storage.runFile(f);
                bot = new Duke(previous);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bot == null) {
            bot = new Duke();
        }

        Ui.showWelcomeMessage(bot);

        return bot;
    }


    /**
     * Updates task list based on user command.
     * @param userCommand user command
     * @param bot a Duke object that manages task list operations
     * @throws IOException if error occurs while writing to the file
     */
    public static void runUserCommand(Command userCommand, Duke bot) throws IOException{
            if (userCommand instanceof ListCommand) {
                bot.showTasks();
            } else if (userCommand instanceof DoneCommand) {
                bot.markAsDone(((DoneCommand) userCommand).getTaskNumber());
                Storage.saveFile(f, bot);
            } else if (userCommand instanceof TodoCommand) {
                bot.addTask(((TodoCommand) userCommand).getDescription(),
                        userCommand.getCommand(), null);
                Storage.saveFile(f, bot);
            } else if (userCommand instanceof DeadlineCommand) {
                bot.addTask(((DeadlineCommand) userCommand).getDescription(), userCommand.getCommand(),
                        ((DeadlineCommand) userCommand).getDeadline());
                Storage.saveFile(f, bot);
            } else if(userCommand instanceof EventCommand) {
                bot.addTask(((EventCommand) userCommand).getDescription(), userCommand.getCommand(),
                        ((EventCommand) userCommand).getEventTime());
                Storage.saveFile(f, bot);

            } else if (userCommand instanceof DeleteCommand) {
                bot.removeTask(((DeleteCommand) userCommand).getTaskNumber());
                Storage.saveFile(f, bot);
            }

    }

    /**
     * Runs application until user inputs exit command.
     * @throws IOException if user input is invalid
     */
    public static void runMain() throws IOException{
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

            if(!(userCommand instanceof ByeCommand)) {
                runUserCommand(userCommand, bot);
            } else {
                exit();
                break;
            }

            count++;
            Ui.printEmptyLine();
        }

    }

    /**
     * Prints exit message.
     */
    public static void exit() {
        Ui.showExitMessage();
    }
}
