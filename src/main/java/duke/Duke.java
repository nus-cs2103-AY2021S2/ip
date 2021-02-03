package duke;

import duke.exceptions.*;
import duke.handler.*;
import duke.tasks.*;
import duke.tasks.Event;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean exit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" d.MMM.yyyy HH:mm");

    private Duke(String storagePath) {
        storage = new Storage(storagePath);
        tasks = storage.load();
        ui = new Ui();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.displayIntro();
        while (exit == false) {
            try {
                CommandHandler commandHandler = Parser.parseFromInput(ui.readCommand());
                checkDeleteDonePossible(commandHandler, tasks);
                commandHandler.execute(ui, storage, tasks);
                if (commandHandler instanceof ByeHandler) {
                    exit = true;
                }
            } catch (DukeException e) {
                String output = e.getMessage();
                ui.respond(output);
            }
        }
        ui.close();
    }

    public static void checkDeleteDonePossible(CommandHandler commandHandler, TaskList taskList)
            throws DukeInvalidDesException {
        if (commandHandler instanceof DoneHandler) {
            if (((DoneHandler) commandHandler).getTaskNum() > taskList.getNumOfTasks()) {
                throw new DukeInvalidDesException("DONE");
            }
        } else if (commandHandler instanceof DeleteHandler) {
            if (((DeleteHandler) commandHandler).getTaskNum() > taskList.getNumOfTasks()) {
                throw new DukeInvalidDesException("DELETE");
            }
        }
    }
}