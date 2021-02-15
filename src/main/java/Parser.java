public class Parser {


    public boolean stillHaveCommands(String command) {
        return !command.equalsIgnoreCase("bye");
    }

    public boolean isStart(String command) {
        return command.equalsIgnoreCase("hello");
    }

    public boolean isList(String command) {
        return command.startsWith("list");
    }

    public boolean isTodo(String command) {
        return command.startsWith("todo");
    }

    public boolean isDeadline(String command) {
        return command.startsWith("deadline");
    }

    public boolean isEvent(String command) {
        return command.startsWith("event");
    }

    public boolean isDelete(String command) {
        return command.startsWith("delete");
    }

    public boolean isFind(String command) {
        return command.startsWith("find");
    }

    public boolean isBye(String command) {
        return command.startsWith("bye");
    }

    public boolean isDone(String command) {
        return command.startsWith("done");
    }

}
