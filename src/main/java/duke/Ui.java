package duke;

import java.util.Scanner;

public class Ui {

    private String botName;
    private Scanner scanner;

    /**
     * Creates a UI "engine" which is responsible for everything related to bot's user interface (UI)
     * @param botName Bot will reply with this bot name in this future
     */
    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Appends the bot name to the intended message. This results in the message looking similar to speech.
     * @param message Message that bot wants to tell user
     */
    public String speak(String message) {
        return (botName + ": " + message);
    }

    /**
     * Receive input from standard input
     * @return Input from standard input
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }
}
