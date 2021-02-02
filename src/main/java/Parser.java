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
        String[] tokens = input.split(" ");
        this.command = tokens[0];
        this.description = input;
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
            if (this.command.equals("list")) {
                return command;
            } else if (this.command.equals("done") || this.command.equals("delete")
                    || this.command.equals("todo") || this.command.equals("find")) {
                return this.description.substring(this.command.length() + 1);
            } else if (this.command.equals("deadline")) {
                String[] phrases = this.description.split(" /by ");
                return phrases[0].substring(this.command.length() + 1);
            } else if (this.command.equals("event")) {
                String[] phrases = this.description.split(" /at ");
                return phrases[0].substring(this.command.length() + 1);
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
            if (this.command.equals("done") || this.command.equals("delete")
                    || this.command.equals("todo") || this.command.equals("list")
                    || this.command.equals("find")) {
                return "";
            } else if (this.command.equals("deadline")) {
                String[] phrases = this.description.split(" /by ");
                return phrases[1];
            } else if (this.command.equals("event")) {
                String[] phrases = this.description.split(" /at ");
                return phrases[1];
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
