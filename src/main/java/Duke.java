import java.io.IOException;

public class Duke {
    private static TaskList tasks;
    private static FileHandler fh;
    private static Ui ui;

    public Duke() {
        ui = new Ui();
        fh = new FileHandler();
        tasks = new TaskList();
    }

    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                end();
                return ui.printEnd();
            } else {
                Command c = Parser.parse(input);
                String response = c.execute(tasks);
                return response;
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }

    public static void run() {
        String fullCommand = ui.readCommand();
        while (!fullCommand.equals("bye")) {
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                fullCommand = ui.readCommand();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
                fullCommand = ui.readCommand();
            }
        }
        end();
    }

    public static void end() {
        writeData();
    }

    public static void readData() {
        try {
            //reading input and putting into list
            tasks = fh.readFromFile(tasks);
        } catch (DukeException ex) {
            ui.showError(ex.getMessage());
        } catch (IOException ex) {
            ui.showError("Ooh lah lah! Something went wrong: " + ex.getMessage());
        }
    }

    public static void writeData() {
        try {
            fh.writeToFile(tasks);
        } catch (IOException ex) {
            ui.showError("Ooh lah lah! Something went wrong: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
