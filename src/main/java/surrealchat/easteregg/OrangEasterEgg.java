package surrealchat.easteregg;

/**
 * EasterEgg subclass for Orang.
 */
public class OrangEasterEgg extends EasterEgg {
    /**
     * Creates new OrangEasterEgg object.
     */
    public OrangEasterEgg() {
        super("orang");
    }

    /**
     * Executes orang easteregg command to give hilarious output regarding the nature of CS2103T.
     * @return String of hilarious output.
     */
    public String execute() {
        String outputString = "Meme Man: ORANG! IT S U...\n";
        outputString += "Orang: No you can't SU\n";
        outputString += "Meme Man: ANGERY!\n";
        return outputString;
    }

    /**
     * Describes usage of orang easteregg command.
     * @return String describing the orang easteregg command.
     */
    public static String displayHelp() {
        return "Gives a hilarious output. Try it out!.\n";
    }
}
