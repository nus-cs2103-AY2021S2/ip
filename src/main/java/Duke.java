import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class Duke {
    private DukeList list;
    private UI ui;
    private Storage storage;

    public static void main(String[] args)  {
       new Duke("data/duke.txt").run();
    }
    public Duke(String filePath)  {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            list = new DukeList(storage.load());
        } catch (FileNotFoundException e) {
            list = new DukeList();
        }
    }
    public void run()  {
        ui.WelcomeMessage();
        ui.showDivider();
        while(!ui.getIsExit()) {
            try {
                String fullCommand = ui.readCommand();
                ui.commandMessage(Parser.parse(fullCommand, list), list);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showMissingArgsError();
            } catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException e) {
                ui.showWrongArgsError();
            }
            ui.showDivider();
        }
        storage.save(list);
        ui.GoodByeMessage();
    }
}
