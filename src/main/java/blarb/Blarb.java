package blarb;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * {@code Blarb} is the chat bot in used in the {@code Main} program.
 */
public class Blarb {
    private final Tasklist tasklist;
    private final String filePath;
    private final Ui ui;
    private final Storage storage;
    private final Processor processor;

    /**
     * Initializes Blarb with a given storage path.
     *
     * @param filePath The storage file path
     */
    public Blarb(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        tasklist = new Tasklist();
        processor = new Processor();

        try {
            tasklist.addAll(storage.load());
        } catch (IOException ex) {
            ui.warn("Cannot initialize task list from storage.");
        } catch (InputMismatchException ex) {
            ui.warn("File is corrupted. Cannot initialize task list.");
        }
    }

    /**
     * Initializes Blarb.
     */
    public Blarb() {
        this("data/tasklist.txt");
    }


    /**
     * Runs Blarb in an input loop.
     */
    public void run() {
        ui.rollCredits();
        ui.blurt("This is BLARB.\nYou may speak.");
        boolean isActive = true;
        while (isActive && ui.isAvailable()) {
            isActive = isActiveAfterExecuting(ui.read());
        }
    }

    /**
     * Executes the command for Blarb(Text-UI).
     *
     * @param input The inputted command.
     * @return A boolean value that shows the availability for the next command intake.
     */
    public boolean isActiveAfterExecuting(String input) {
        Output output = processor.execute(input, tasklist, storage);
        if (!processor.willTerminate(input)) {
            output.warn.ifPresent(ui::warn);
            ui.blurt(output.normal);
            return true;
        }
        ui.adios();
        return false;
    }

    /**
     * Gets the response of Blarb(GUI).
     *
     * @param input User input string.
     * @return Response string.
     */
    public String getResponse(String input) {
        Output output = processor.execute(input, tasklist, storage);
        String normal = output.normal;
        String warn = output.warn
                .map(x -> String.format("!!! %s\n", x))
                .orElse("");
        return String.format("%s%s", warn, normal);
    }

    /**
     * Checks if Blarb(GUI) should terminate.
     *
     * @param input User input string.
     * @return Response string.
     */
    public boolean willTerminate(String input) {
        return processor.willTerminate(input);
    }

}
