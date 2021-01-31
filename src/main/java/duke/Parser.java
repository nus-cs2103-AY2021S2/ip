package duke;

/**
 * This class translate the user input into commands understandable by Duke.
 */
public class Parser {

    public static int indexDetermine(String command, String key) {
        return (!command.contains(key)) ? command.length() : command.indexOf(key);
    }
    /**
     * Parse the user input.
     * @param command input entered by user
     */
    public static String parse(String command, TaskList taskList, Storage storage) throws DukeException {
        //command = command.trim();
        if (command.equals("list")) {
            return Executor.list(taskList);
        } else if (command.equals("bye")) {
            return Executor.exit(taskList, storage);
        }
        String type, desc, time;
        if (!command.contains(" ")) {
            throw new DukeException("Missing argument(s)!");
        } else {
            type = command.substring(0, command.indexOf(" "));
            desc = command.substring(command.indexOf(" ") + 1);
        }

        if (type.equals("todo")) {
            return Executor.add(taskList, type, desc);
        } else if (type.equals("deadline") || type.equals("event")) {
            if (!desc.contains("/")) {
                throw new DukeException("Missing argument(s)!");
            } else {
                time = desc.substring(desc.indexOf("/") + 4, desc.length());
                desc = desc.substring(0, desc.indexOf("/") - 1);
            }
            return Executor.add(taskList, type, desc, time);
        } else if (type.equals("done")) {
            return Executor.markDone(taskList, Integer.parseInt(desc) - 1);
        } else if (type.equals("delete")) {
            return Executor.delete(taskList, Integer.parseInt(desc) - 1);
        } else if (type.equals("find")) {
            return Executor.find(taskList, desc);
        } else if (type.equals("list")) {
            return Executor.list(taskList);
        } else if (type.equals("bye")) {
            return Executor.exit(taskList, storage);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
