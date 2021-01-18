package misc;

/**
 * The enum Emoji.
 */
public enum Emoji {
    /**
     * Task emoji.
     */
    TASK("\uD83D\uDCC5"),
    /**
     * Error emoji.
     */
    ERROR("\u26A0");

    /**
     * Unicode for emoji.
     */
    public final String code;

    Emoji(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
