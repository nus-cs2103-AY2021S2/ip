public enum Commands {
    BYE("bye"), LIST("list"), DONE("done");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}