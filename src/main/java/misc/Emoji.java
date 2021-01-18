package misc;

/**
 * The enum Emoji.
 */
public enum Emoji {
    TASK("\uD83D\uDCC5"),
    TRASH("\uD83D\uDEAE"),
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
