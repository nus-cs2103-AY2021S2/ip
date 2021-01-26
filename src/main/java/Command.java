public class Command {
    Com command;
    String arguments;

    Command(Com command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    Com getCommand() {
        return this.command;
    }
    String getArguments() {
        return this.arguments;
    }
}
