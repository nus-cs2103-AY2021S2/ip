package duke;

/**
 * This is the Main class for Duke.
 */
public class Duke {

    public static String getGreeting() {
        return UI.loadAndSayHello();
    }

    public static String getGoodbye() {
        return UI.saveAndGoodBye();
    }

    public String getResponse(String input) {
        return UI.respond(input);
    }

}
