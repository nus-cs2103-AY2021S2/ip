package duke;

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
    private final static File f = new File("duke.txt");
    private final static Parser parser = new Parser();

    public static void main(String[] args) throws IOException{
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
        int count = 0;

        Ui.showWelcomeMessage(bot);

        while (true) {
            if (count > 0) {
                Ui.askForUserRequest();
            }
            String string = Ui.readUserInput();
            try {
                parser.processInput(string, bot);
            } catch (InvalidCommandException | InvalidArgumentException ex) {
                Ui.print(ex.getMessage());
                continue;
            }

            if (!(parser.isEquals("bye"))) {
                if (parser.isEquals("list")) {
                    bot.showTasks();
                } else if (parser.isEquals("done")) {
                    bot.markAsDone(parser.description);
                    Storage.saveFile(f, bot);
                } else if (parser.isEquals("todo")) {
                    bot.addTask(parser.description, parser.getCommand(), null);
                    Storage.saveFile(f, bot);
                } else if (parser.isEquals("deadline") || parser.isEquals("event")) {
                    bot.addTask(parser.description, parser.getCommand(), parser.getDeadline());
                    Storage.saveFile(f, bot);
                } else if (parser.isEquals("delete")) {
                    bot.removeTask(parser.description);
                    Storage.saveFile(f, bot);
                }
            } else {
                Ui.showExitMessage();
                break;
            }
            count++;
            Ui.printEmptyLine();
        }
    }
}
