import java.util.ArrayList;

/**
   * Parser only has one method parse
   *
   * This method processes the users input and
   * outputs commands to be executed later on
   */
public class Parser {

    public static Command parse(String command, ArrayList<Command> pastCommands) throws DukeException {
        String[] lineSplit = command.split(" ", 2);
        switch (lineSplit[0]) {
        case ("bye"):
            Command exit = new ExitCommand("", "", "");
            return exit;
            //exitcommand
        case ("list"):
            int length = lineSplit.length;
            if (length > 1) {
                Command list = new ListCommand("list", "", lineSplit[1]);
                return list;
            } else {
                assert length == 1;
                Command list = new ListCommand("list", "", "");
                return list;
            }
        case ("done"):
            int index = Integer.valueOf(lineSplit[1]) - 1;
            Command done = new DoneCommand("done", Integer.toString(index), "");
            return done;
        case ("delete"):
            int index2 = Integer.valueOf(lineSplit[1]) - 1;
            Command delete = new DeleteCommand("delete", Integer.toString(index2), "");
            return delete;
        case ("find"):
            Command find = new FindCommand("find", lineSplit[1], "");
            return find;
        case ("todo"):
            try {
                String[] item = lineSplit[1].split("/");
                Command todo = new AddCommand("todo", item[0], "");
                return todo;
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DukeException(
                        "\u00a9 OOPS!!! The description of a todo cannot be empty.");
            }
        case ("deadline"):
            try {
                String[] item = lineSplit[1].split("/by ");
                Command deadline = new AddCommand("deadline", item[0], item[1]);
                return deadline;
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DukeException(
                         "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
            }
        case ("event"):
            try {
                String[] item = lineSplit[1].split("/at ");
                Command deadline = new AddCommand("event", item[0], item[1]);
                return deadline;
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DukeException(
                        "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
            }
        case ("undo"):

            Command undo = new UndoCommand("undo", "", "", pastCommands);
            return undo;
        default:
            return null;
        }
    }
}




