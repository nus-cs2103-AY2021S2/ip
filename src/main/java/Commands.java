public enum Commands {
    TODO("todo"), DEADLINE("deadline"),
    EVENT("event"), DONE("done"),
    LIST("list"), DELETE("delete"),
    HELP("help"), BYE("bye"),
    INVALID_COMMAND(null);

    private String commandString;

    private Commands (String commandString) {
        this.commandString = commandString;
    }

    public String getCommandStr() {
        return this.commandString;
    }

    public static Commands get(String str) {
        try {
            Commands command = Commands.valueOf(commandsToUpperCase(str));
            return command;
        } catch (IllegalArgumentException e) {
            return INVALID_COMMAND;
        }

    }

    private static String commandsToUpperCase(String command) {
        return command.toUpperCase();
    }
}
