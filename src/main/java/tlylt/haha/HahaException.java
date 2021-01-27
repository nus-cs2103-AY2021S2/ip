package tlylt.haha;

/**
 * Custom exception for Haha.
 */
public abstract class HahaException extends Exception {
    private final String command;

    HahaException(String command) {
        this.command = command;
    }

    /**
     * Returns the actual incorrect user input
     *
     * @return String problematic input
     */
    public String getCommand() {
        return command;
    }

    /**
     * Specifies all subclass to have specific string representation.
     *
     * @return String error message.
     */
    public abstract String toString();
}
