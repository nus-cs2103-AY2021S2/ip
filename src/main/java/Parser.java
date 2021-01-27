public class Parser {
    private String command;
    private String description;

    Parser(String input) {
        String[] tokens = input.split(" ");
        this.command = tokens[0];
        this.description = input;
    }

    public String getCommand() {
        return this.command;
    }

    public String getDescription() throws DukeException{
        try {
            if (this.command.equals("list")) {
                return command;
            }
            if (this.command.equals("done") || this.command.equals("delete") || this.command.equals("todo")
                    || this.command.equals("find")) {
                return this.description.substring(this.command.length() + 1);
            } else if (this.command.equals("deadline")) {
                String[] phrases = this.description.split(" /by ");
                return phrases[0].substring(this.command.length() + 1);
            } else if (this.command.equals("event")) {
                String[] phrases = this.description.split(" /at ");
                return phrases[0].substring(this.command.length() + 1);
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the " + this.command +" must have description :-(");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the input is kinda faulty :-(");
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I cannot understand the input :-(");
    }


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
            throw new DukeException("☹ OOPS!!! I'm sorry, but the " + this.command +" must have description :-(");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the input is kinda faulty :-(");
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I cannot understand the input :-(");
    }
}
