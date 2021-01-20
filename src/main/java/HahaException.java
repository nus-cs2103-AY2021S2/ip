public abstract class HahaException extends Exception {
    private final String command;

    HahaException(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public abstract String toString();
}
