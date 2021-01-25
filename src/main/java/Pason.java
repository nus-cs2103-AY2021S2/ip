import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Pason {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Pason() {
        storage = new Storage();
        ui = new Ui(System.in);
        try {
            tasks = new TaskList(storage);
        } catch(PasonException e) {
            ui.printMessage(e.getMessage());
        } catch(Exception e) {
            ui.printMessage(e.getMessage());
        }

    }

    public void run() {
        ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommand = ui.readCommand();
                Command command = Parser.parseCommand(inputCommand);
                command.execute(tasks, storage, ui);
                isExit = command.isExit();
            } catch(PasonException e) {
                ui.printMessage(e.getMessage());
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Pason().run();
    }
}
