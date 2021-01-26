public class Parser {
    public Parser() {}

    public static boolean isNumber(String str) {
        // check for null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public String[] processInput(String inputLine) throws DukeException{
        String[] result = new String[5];

        if (inputLine.equals("list")) {
            result[0] = "LST";
        } else if (inputLine.equals("bye")) {
            result[0] = "BYE";
        } else if (inputLine.equals("done")) {
            throw new DukeException("The task number of a done cannot be empty. Please try again.");
        } else if (inputLine.startsWith("done")) {
            String index = inputLine.substring(5);
             if (isNumber(index)) {
                 result[0] = "DON";
                 result[1] = index;
             } else {
                 throw new DukeException("Please enter a numerical task number.");
             }
        } else if (inputLine.startsWith("todo")) {
            if (inputLine.equals("todo")) {
                throw new DukeException("Please enter a todo description.");
            } else {
                String todoDesc = inputLine.substring(5);
                result[0] = "TDO";
                result[1] = todoDesc;
            }
        } else if (inputLine.startsWith("deadline")) {
            if (inputLine.equals("deadline")) {
                throw new DukeException("Please enter a deadline description.");
            } else {
                String dlMsg = inputLine.substring(9);
                String[] temp = dlMsg.split(" /by ");
                if (temp.length == 1) {
                    throw new DukeException("Please enter a deadline completion time.");
                } else {
                    String dlDesc = temp[0];
                    String by = temp[1];
                    result[0] = "DDL";
                    result[1] = dlDesc;
                    result[2] = by;
                }
            }
        } else if (inputLine.startsWith("event")) {
            if (inputLine.equals("event")) {
                throw new DukeException("Please enter an event description.");
            } else {
                String dlMsg = inputLine.substring(6);
                String[] temp = dlMsg.split(" /at ");
                if (temp.length == 1) {
                    throw new DukeException("Please enter an event time.");
                } else {
                    String evDesc = temp[0];
                    String at = temp[1];
                    result[0] = "ENT";
                    result[1] = evDesc;
                    result[2] = at;
                }
            }
        } else if (inputLine.startsWith("delete")) {
            if (inputLine.equals("delete")) {
                throw new DukeException("Please tell me which task you'd like to delete.");
            } else {
                String temp = inputLine.substring(7);
                if (isNumber(temp)) {
                    result[0] = "DLT";
                    result[1] = temp;
                } else {
                    throw new DukeException("Please enter a numerical task number.");
                }
            }
        } else {
            throw new DukeException("I'm sorry, I don't understand what that means.");
        }
        return result;
    }
}
