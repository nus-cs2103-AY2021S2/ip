/**
 * A class that intended to parse the command from the users in DUke application.
 */
public class Parser {
    private String command;
    private String description;

    /**
     * Constructor
     * @param input String given by the user
     */
    Parser(String input) {
        String cleanedInput = input.trim();
        String[] tokens = cleanedInput.split(" ");
        this.command = tokens[0];
        this.description = cleanedInput;
    }

    /**
     * Get the type of command from the user
     * @return Type of command from the user
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Get the description of the task
     * @return String representing the description of the task
     * @throws DukeException Empty description or unknown command
     */
    public String getDescription() throws DukeException{
        try {
            switch (command){
            case "list":
                if (this.description.length() != "list".length()) {
                    throw new DukeException("OOPS!!! I'm sorry, but list shouldn't have any additional arguments");
                }
                return "";
            case "help":
                if (this.description.length() != "list".length()) {
                    throw new DukeException("OOPS!!! I'm sorry, but list shouldn't have any additional arguments");
                }
                return "";
            case "sort":
                if (this.description.length() != "sort".length()) {
                    throw new DukeException("OOPS!!! I'm sorry, but list shouldn't have any additional arguments");
                }
                return "";
            case "undo":
                return this.description.length() > 4 ? this.description.substring(5) : "";
            case "statistics":
                return this.description.length() > 10 ? this.description.substring(11) : "";
            case "done":
                return this.description.substring(5);
            case "delete":
                return this.description.substring(7);
            case "todo":
                return this.description.substring(5);
            case "find":
                return this.description.substring(5);
            case "deadline":
                String[] phrasesFromDeadline = this.description.split(" /by ");
                return phrasesFromDeadline[0].substring(this.command.length() + 1);
            case "event":
                String[] phrasesFromEvent = this.description.split(" /at ");
                return phrasesFromEvent[0].substring(this.command.length() + 1);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, but the "
                    + this.command +" must have description :-(");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, but the input is kinda faulty :-(");
        }
        throw new DukeException("OOPS!!! I'm sorry, but I cannot understand the input :-(");
    }

    /**
     * Get the deadline of the task (if any)
     * @return String representing the deadline of the task
     * @throws DukeException Empty description or unknown command
     */
    public String getDeadLine() throws DukeException {
        try {
            switch (command){
            case "list":
                return "";
            case "help":
                return "";
            case "sort":
                return "";
            case "done":
                return "";
            case "delete":
                return "";
            case "todo":
                return "";
            case "find":
                return "";
            case "undo":
                return "";
            case "statistics":
                return "";
            case "deadline":
                String[] phrasesFromDeadline = this.description.split(" /by ");
                return phrasesFromDeadline[1];
            case "event":
                String[] phrasesFromEvent = this.description.split(" /at ");
                return phrasesFromEvent[1];
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, but the "
                    + this.command +" must have description :-(");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, but the input is kinda faulty :-(");
        }
        throw new DukeException("OOPS!!! I'm sorry, but I cannot understand the input :-(");
    }
}
