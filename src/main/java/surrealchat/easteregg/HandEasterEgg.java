package surrealchat.easteregg;

/**
 * EasterEgg subclass for Hand.
 */
public class HandEasterEgg extends EasterEgg {
    /**
     * Creates new HandEasterEgg object.
     */
    public HandEasterEgg() {
        super("HAND");
    }

    /**
     * Executes icandoit or aikendueet easteregg command to give hilarious output about HAND.
     * @return String of hilarious output.
     */
    public String execute() {
        return "HAND\n";
    }

    /**
     * Describes usage of icandoit or aikendueet easteregg command.
     * @return String describing the icandoit or aikendueet easteregg command.
     */
    public static String displayHelp() {
        return "Gives a hilarious output. Try it out!.\n";
    }
}
