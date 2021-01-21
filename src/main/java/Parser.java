import java.util.Locale;

public class Parser {
    Parser() {}

    public static Command parseCommand(String input) throws DukeException {
        int endIndex = input.indexOf(" ");
        String potentialCommand = (endIndex == -1) ? input : input.substring(0, endIndex);
        Command command;

        if (potentialCommand.toUpperCase().equals("TODO")) {
            command = Command.TODO;
        } else if (potentialCommand.toUpperCase().equals("DEADLINE")) {
            command = Command.DEADLINE;
        } else if (potentialCommand.toUpperCase().equals("EVENT")) {
            command = Command.EVENT;
        } else if (potentialCommand.toUpperCase().equals("LIST")) {
            command = Command.LIST;
        } else if (potentialCommand.toUpperCase().equals("DONE")) {
            command = Command.DONE;
        } else if (potentialCommand.toUpperCase().equals("BYE")) {
            command = Command.BYE;
        } else {
            throw new CommandNotFoundException("What do you mean? I do not know this command.");
        }
        return command;
    }

    public static int getDoneIndex(String input) throws DescriptionMissingException {
        if (input.length() < 6 || !Character.isDigit(input.charAt(5))) {
            throw new DescriptionMissingException("The index of the task done?");
        } else {
            return Character.getNumericValue(input.charAt(5)) - 1;
        }
    }

    public static Todo getTodo(String input) throws DescriptionMissingException {
        if (input.length() < 6) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(5);
            return new Todo(description);
        }
    }

    public static Deadline getDeadline(String input) throws DescriptionMissingException {
        if (input.length() < 10) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(9);
            int index = description.indexOf(" /by");
            if (index == -1) {
                throw new DescriptionMissingException("Invalid input");
            } else {
                try {
                    String name = description.substring(0, index);
                    String ddl = description.substring(index + 5);
                    return new Deadline(name, ddl);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DescriptionMissingException("Missing description?");
                }
            }
        }
    }

    public static Event getEvent(String input) throws DescriptionMissingException {
        if (input.length() < 7) {
            throw new DescriptionMissingException("No description?");
        } else {
            String description = input.substring(6);
            int index = description.indexOf(" /at");
            if (index == -1) {
                throw new DescriptionMissingException("Invalid input");
            } else {
                try {
                    String name = description.substring(0, index);
                    String time = description.substring(index + 5);
                    return new Event(name, time);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DescriptionMissingException("Missing description?");
                }
            }
        }
    }
}
