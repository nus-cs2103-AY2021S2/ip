package duke;

import duke.command.CommandParser;
import duke.storage.Storage;
import duke.task.Reminder;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;

/**
 * This is the main Duke class that runs the chatbot.
 */
public class Duke{
    private final Ui ui;
    private final Storage fio;
    /**
     * This is the constructor for Duke, storing a new Ui and new Storage.
     */
    public Duke() {
        this.ui = new Ui();
        this.fio = new Storage();
    }

    public String startIntro() {
        return this.ui.getIntroResponse();
    }

    public String startReminders() {
        return this.ui.getReminders(this.fio.getRemindersArr());
    }

    public String beginClose() {
        this.fio.beginClose();
        this.fio.closeFile();
        return (this.ui.getGoodbyeResponse());
    }

    public String getResponse(String input) {
        String output;
        try {
            CommandParser parsedCommands = CommandParser.parseLine(input, this.fio.getArrSize());
            output = parsedCommands.executeCommand(ui, this.fio);
        } catch (DukeException e) {
            output = ui.getErrorResponse(e.toString());
        }
        return output;
    }
}
