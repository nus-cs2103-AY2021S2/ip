import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /*public void run() {
        Scanner sc = new Scanner(System.in);
        ui.reply();
        storage.loadTasks(tasks,);
        File file = new File("data/DukeData.txt");
        File dir = new File("data/");
        try {
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            storage.loadTasks(tasks, file);
        } catch (Exception e) {
            e.getStackTrace();
        }
        Parser parser = new Parser(tasks, ui);
        while (true) {
            String command = sc.nextLine();
            parser.insertCommand(command);
            parser.process();
            if (parser.isEnd()) {
                break;

            }
        }
        System.exit(0);
    }*/

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.reply();
        Parser parser = new Parser(tasks, ui);
        while (true) {
            String command = sc.nextLine();
            parser.insertCommand(command);
            parser.process();
            if (parser.isEnd()) {
                break;
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws IOException{
        new Duke("data/DukeData.txt").run();
    }
}