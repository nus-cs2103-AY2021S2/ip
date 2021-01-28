import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class DukeSimulator {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public DukeSimulator() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
    }


    public void run() {
        ui.printGreeting();
        storage.load(tl);
        String input;
        input = ui.readLine();
        while(!input.equals("bye")) {
            Command c = Parser.parseInput(input);
            c.execute(tl, ui, storage);
            input = ui.readLine();
        }
        ui.printBye();
    }


    private Task toDoMaker(String command) throws DukeMissingDescriptionException {
        if(command.equals("")) {
            throw new DukeMissingDescriptionException("todo");
        } else {
            return new ToDo(command);
        }
    }

    private Task deadlineMaker(String command) throws DukeWrongFormatException,
            DukeMissingDescriptionException {
        String[] parsedCmd = command.split(" /by ", 2);
        if(parsedCmd.length != 2) {
            throw new DukeWrongFormatException("deadline");
        } else if(parsedCmd[0].equals(" ") || parsedCmd[1].equals(" ")) {
            throw new DukeMissingDescriptionException("deadline");
        } else {
            try {
                LocalDateTime ldt = LocalDateTime.parse(parsedCmd[1],
                        DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
                return new Deadline(parsedCmd[0], ldt);
            } catch (DateTimeParseException e) {
                throw new DukeWrongFormatException("deadline");
            }
        }
    }

    private Task eventMaker(String command) throws DukeWrongFormatException,
            DukeMissingDescriptionException {
        String[] parsedCmd = command.split(" /at ", 2);
        if(parsedCmd.length != 2) {
            throw new DukeWrongFormatException("event1");
        } else if(parsedCmd[0].equals(" ") || parsedCmd[1].equals(" ")) {
            throw new DukeMissingDescriptionException("event");
        } else {
            try {
                String[] parsedDate = parsedCmd[1].split(" ");
                String date = parsedDate[0];
                String[] parsedTime = parsedDate[1].split("-");
                LocalDateTime ldtStart = LocalDateTime.parse(date + " " + parsedTime[0],
                        DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
                LocalDateTime ldtEnd = LocalDateTime.parse(date + " " + parsedTime[1],
                        DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
                return new Event(parsedCmd[0], ldtStart, ldtEnd);
            } catch (Exception e) {
                throw new DukeWrongFormatException("event");
            }
        }
    }

}