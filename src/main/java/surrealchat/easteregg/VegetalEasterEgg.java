package surrealchat.easteregg;

/**
 * EasterEgg subclass for Vegetal.
 */
public class VegetalEasterEgg extends EasterEgg {
    /**
     * Creates new VegetalEasterEgg object.
     */
    public VegetalEasterEgg() {
        super("vegetal");
    }

    /**
     * Executes vegetal easteregg command to give hilarious output about someone saying no vegetals.
     * @return String of hilarious output.
     */
    public String execute() {
        String outputString = "Vegetal: Did someone said... NO VEGETALS?\n";
        outputString += "Meme Man: I taste a vegetal... ANGERY!\n";
        return outputString;
    }

    /**
     * Describes usage of vegetal easteregg command.
     * @return String describing the vegetal easteregg command.
     */
    public static String displayHelp() {
        return "Gives a hilarious output. Try it out!.\n";
    }
}
