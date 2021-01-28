/**
   * Parser only has one method parse
   * 
   * This method processes the users input and
   * outputs commands to be executed later on
   */
public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] lineSplit = command.split(" ", 2);
        if (lineSplit[0].equals("bye")) {
            Command exit = new ExitCommand("", "", "");
            return exit;
            //exitcommand
        } else if (lineSplit[0].equals("list")) {
            int length = lineSplit.length;
            
            if (length > 1) {
                Command list = new ListCommand("list", "", lineSplit[1]);
                return list;
            } else {
                Command list = new ListCommand("list", "", "");
                return list;
            }
        } else if (lineSplit[0].equals("done")) {
            int index = Integer.valueOf(lineSplit[1]) - 1;
            Command done = new DoneCommand("done", Integer.toString(index), "");
            return done;
            
        } else if (lineSplit[0].equals("delete")) {
            int index = Integer.valueOf(lineSplit[1]) - 1;
            Command delete = new DeleteCommand("delete", Integer.toString(index), "");
            return delete;
            
        } else if (lineSplit[0].equals("find")) {
            Command find = new FindCommand("find", lineSplit[1], "");
            return find;
            
        }else if (lineSplit[0].equals("todo")) {
            try {
                String[] item = lineSplit[1].split("/");
                Command todo = new AddCommand("todo", item[0], "");
                return todo;   
               } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new DukeException(
                            "\u00a9 OOPS!!! The description of a todo cannot be empty.");
                }
            
            } else if (lineSplit[0].equals("deadline")) {
                try {
                    String[] item = lineSplit[1].split("/by ");
                    Command deadline = new AddCommand("deadline", item[0], item[1]);
                    return deadline;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new DukeException(
                            "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
                }
            
            } else if (lineSplit[0].equals("event")) {
                try {
                    String[] item = lineSplit[1].split("/at ");
                    Command deadline = new AddCommand("event", item[0], item[1]);
                    return deadline;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new DukeException(
                            "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
                }
            
        }
        
        return null;
    }

    
    
}
