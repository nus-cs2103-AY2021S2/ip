public enum Command {
    EXIT("bye");

    private String command;

    Command(String inputCommand) {
        this.command = inputCommand;
    }

    public String getCommand() {
        return command;
    }
}
