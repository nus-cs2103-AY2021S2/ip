package duke;

import java.io.IOException;

import commands.Command;
import commands.ExitCommand;
import data.TaskList;
import parser.Parser;
import parser.ParserException;
import storage.StorageFile;
import ui.TextUi;

public class Duke {
    /**
     * Entry point for Duke.Duke
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TextUi ui = new TextUi();
        Parser parser = new Parser();
        StorageFile storage = new StorageFile();

        TaskList tasks = storage.load();
        ui.writeGreeting();

        Command command = null;
        do {
            String input = ui.readLine();

            try {
                command = parser.parseCommand(input);
            } catch (ParserException pe) {
                ui.write(pe.getMessage());
                continue;
            }

            command.execute(tasks, ui);
            storage.save(tasks);
        } while (!ExitCommand.isExit(command));

        ui.close();
    }


}
