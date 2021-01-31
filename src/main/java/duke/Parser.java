package duke;

/**
 * This class translate the user input into commands understandable by Duke.
 */
public class Parser {

    /**
     * Parse the user input.
     * @param command input entered by user
     */
    public static String parse(String command, TaskList taskList, Storage storage) throws DukeException {
        command = command.trim();
        String type = command.substring(0, command.indexOf(" "));
        String desc = command.substring(command.indexOf(" ") + 1,
                ((!command.contains("/")) ? command.length() : command.indexOf("/")));
        String time = command.substring(command.indexOf("/") + 4);
        String ret;
        if (type.equals("todo")) {
            ret = Executor.add(taskList, type, desc);
        } else if (type.equals("deadline") || type.equals("event")) {
            ret = Executor.add(taskList, type, desc, time);
        } else if (type.equals("done")) {
            ret = Executor.markDone(taskList, Integer.parseInt(desc));
        } else if (type.equals("delete")) {
            ret = Executor.delete(taskList, Integer.parseInt(desc));
        } else if (type.equals("find")) {
            ret = Executor.find(taskList, desc);
        } else if (type.equals("list")) {
            ret = Executor.list(taskList);
        } else if (type.equals("bye")) {
            ret = Executor.exit(taskList, storage);
        }
        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return ret;
    }
}
