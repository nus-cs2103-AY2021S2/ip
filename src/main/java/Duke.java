import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    SaveToFile fio;
    private ArrayList<Task> arrL;

    public Duke() {
        this.arrL = new ArrayList<>();
        this.ui = new Ui();
        this.fio = new SaveToFile(this.arrL);

    }

    public void run() {
        this.ui.showIntro();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            ParseCommands parseCommands = ParseCommands.parseLine(ui, fullCommand, this.arrL.size());
            parseCommands.executeCommand(ui, this.arrL);
            isExit = parseCommands.getIsExit();
        }
        fio.beginClose(this.arrL);
        fio.closeFile();
    }

    public static void main(String[] args) {

        new Duke().run();
    }
}
