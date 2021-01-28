package duke;

/**
 * The main Duke class.
 */
public class Duke {
    private final Chatbot chatbot;

    Duke() {
        chatbot = new Chatbot();
    }

    /**
     * Triggers the execution of duke.Duke.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke().execute();
    }

    /**
     * Executes all the functions needed for duke.Duke, including welcome,
     * chat bot execution, welfare, and update of file.
     */
    public void execute() {
        System.out.println(Ui.welcome());
        chatbot.execute();
        System.out.println(Ui.welfare());
    }

}
