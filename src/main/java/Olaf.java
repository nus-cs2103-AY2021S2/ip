import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// @@author: VRSoorya
// adapted from GitHub repo nus-cs2103-AY2021S2/ip

public class Olaf {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Olaf(String filePath) {
        Storage storage = new Storage(filePath);

        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        new OlafApp(tasks).run();
    }

    public static void main(String[] args) throws IOException {
        new Olaf("../../../data/olaf.txt").run();
    }
}
