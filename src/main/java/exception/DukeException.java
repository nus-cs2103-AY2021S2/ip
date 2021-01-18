package exception;

import misc.Color;
import misc.Emoji;

/**
 * duke.Duke exceptions handler.
 */
public class DukeException extends Exception {

    /**
     * duke.Duke message dividers.
     */
    public final static String  DIVIDER1 = "\n═══════════════════════════╣"
            + "DUKE╠═══════════════════════════\n";
    /**
     * duke.Duke message dividers.
     */
    public final static String  DIVIDER2 = "\n════════════════════════════"
            + "════════════════════════════════\n";
    /**
     * Instantiates a new duke.Duke exception.
     *
     * @param message the message
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return DIVIDER1 + Color.YELLOW_BOLD + Emoji.ERROR + Color.RESET + " "
                + this.getMessage() + " " + Color.YELLOW_BOLD
                + Emoji.ERROR + Color.RESET + DIVIDER2;
    }
}
