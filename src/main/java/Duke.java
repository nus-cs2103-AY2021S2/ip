import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//to handle date and time
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Duke {
    private TaskList task;
    private Ui ui;
    private FileSaver fs;

    public Duke (String folderName, String fileName) {
        fs = new FileSaver(folderName, fileName);
        ui = new Ui();
        task = new TaskList();
    }
    public void run() {
        ui.greeting();
        try {
            fs.load(task);
        } catch (Exception e) {
            //TODO: handle exception
            ui.printErrorMessage(e.getMessage());
        }
        ui.runUi(task, fs);
        ui.close();
    }
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}
