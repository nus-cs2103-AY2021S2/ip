import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Duke {
    private Storage storage;
    private TaskList list;
    private Ui ui;

    public static void main(String[] args) throws DukeException, IOException {
        TaskList list = new TaskList();
        Storage storage = new Storage("./myData.txt");
        Ui ui = new Ui();
        storage.initialise(list);
        ui.initialise();
        ui.tasksLeft(list);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            Parser.parse(list, str);
            str = sc.nextLine();
        }
        storage.finalise(list);
        ui.finalise();
    }
}
