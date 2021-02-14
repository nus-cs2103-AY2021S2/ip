package duke;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tl;

    /**
     * Constructs new Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tl = this.storage.load();
    }

    /**
     * Returns string of greeting.
     *
     * @return Greeting message.
     */
    public static String greeting() {
        return new Ui().printGreeting();
    }

    /**
     * Returns string of help message.
     *
     * @return Help message.
     */
    public static String help() {
        return new Ui().printHelp();
    }

    /**
     * Returns path of Duke.class jar directory.
     *
     * @return String format of path of Duke.
     * @throws UnsupportedEncodingException When unable to create string of duke's path.
     */
    public static String getPath() throws UnsupportedEncodingException {
        String path = Duke.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8");
        return decodedPath;
    }

    public static void main(String[] args) {

    }

    /**
     * Returns string to print given user input.
     *
     * @param input Input from user.
     * @return Respond from duke in string format.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            return c.execute(tl, ui, storage);
        } catch (Exception e) {
            return ui.printError(e.toString());
        }
    }
}
