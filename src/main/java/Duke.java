import java.io.IOException;
import java.util.*;
import java.io.File;

public class Duke {
    public static final Scanner sc = new Scanner(System.in);
    public static TaskList tasks;
    public static FileHandler fh;
    public static Ui ui;

    public Duke() {
        ui = new Ui();
        fh = new FileHandler();
        tasks = new TaskList();
    }

    public static void start() {
        ui.printStart();
        readData();
    }

    public static void run() {
        start();
        String fullCommand = ui.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                fullCommand = ui.readCommand();
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        end();
    }

    public static void end() {
        writeData();
        ui.printEnd();
    }

    public static void readData() {
        try {
            //reading input and putting into list
            tasks = fh.readFromFile(tasks);
        } catch (DukeException ex) {
            ui.showError(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Oops! Something went wrong: " + ex.getMessage());
        }
    }

    public static void writeData() {
        try {
            fh.writeToFile(tasks);
        } catch (IOException ex) {
            System.out.println("Oops! Something went wrong: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
