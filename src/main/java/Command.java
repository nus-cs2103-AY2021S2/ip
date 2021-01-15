public class Command {
    public static CommandType getType(String str) {
        if (str.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (str.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (str.length() >= 6 && str.substring(0, 4).equalsIgnoreCase("done")
                && str.charAt(4) == ' ') {
            return CommandType.DONE;
        } else {
            return CommandType.ADD;
        }
    }

}
