package blarb;

class Parser {
    static CommandLine parse(String input) {
        String[] tokens = input.split(" ", 2);
        Command command = Command.command(tokens[0]);
        String description;
        if (tokens.length == 1) {
            description = "";
        } else {
            description = tokens[1];
        }
        return new CommandLine(command, description);
    }
}
