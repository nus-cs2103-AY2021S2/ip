package misc;

/**
 * The enum Color.
 */
public enum Color {
    /**
     * Reset color.
     */
    RESET("\033[0m"),
    /**
     * Black color.
     */
    BLACK("\033[0;30m"),
    /**
     * Red color.
     */
    RED("\033[0;31m"),
    /**
     * Green color.
     */
    GREEN("\033[0;32m"),
    /**
     * Yellow color.
     */
    YELLOW("\033[0;33m"),
    /**
     * Blue color.
     */
    BLUE("\033[0;34m"),
    /**
     * Magenta color.
     */
    MAGENTA("\033[0;35m"),
    /**
     * Cyan color.
     */
    CYAN("\033[0;36m"),
    /**
     * White color.
     */
    WHITE("\033[0;37m"),
    /**
     * Black bold color.
     */
    BLACK_BOLD("\033[1;30m"),
    /**
     * Red bold color.
     */
    RED_BOLD("\033[1;31m"),
    /**
     * Green bold color.
     */
    GREEN_BOLD("\033[1;32m"),
    /**
     * Yellow bold color.
     */
    YELLOW_BOLD("\033[1;33m"),
    /**
     * Blue bold color.
     */
    BLUE_BOLD("\033[1;34m"),
    /**
     * Magenta bold color.
     */
    MAGENTA_BOLD("\033[1;35m"),
    /**
     * Cyan bold color.
     */
    CYAN_BOLD("\033[1;36m"),
    /**
     * White bold color.
     */
    WHITE_BOLD("\033[1;37m");

    /**
     * Unicode for colors.
     */
    public final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}