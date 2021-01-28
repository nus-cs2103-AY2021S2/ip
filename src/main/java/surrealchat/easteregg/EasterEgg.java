package surrealchat.easteregg;

/**
 * Base class for easter eggs.
 */
public abstract class EasterEgg {
    private final String easterEggType;

    /**
     * Creates new EasterEgg command.
     * @param easterEggType The type of easter egg.
     */
    public EasterEgg(String easterEggType) {
        this.easterEggType = easterEggType;
    }

    /**
     * Executes easteregg command to give some funny output.
     * @return String to be printed.
     */
    public abstract String execute();
}
