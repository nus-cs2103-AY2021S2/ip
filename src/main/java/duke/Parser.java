package duke;

import java.util.HashSet;

/**
 * This class translate the user input into commands understandable by Duke.
 */
public class Parser {
    private static HashSet<String> acceptedCommands = new HashSet<>();
    static void populateCommands() {
        acceptedCommands.add("todo");
        acceptedCommands.add("deadline");
        acceptedCommands.add("event");
        acceptedCommands.add("done");
        acceptedCommands.add("delete");
        acceptedCommands.add("find");
        acceptedCommands.add("list");
        acceptedCommands.add("bye");
        acceptedCommands.add("empty");
    }

    /**
     * Returns the accepted commands
     * @return a HashSet containing the accepted commands
     */
    public static HashSet<String> getAcceptedCommands() {
        return acceptedCommands;
    }

    /**
     * Parse the user input.
     * @param command input entered by user
     */
    public static String parse(String command, TaskList taskList, Storage storage) throws DukeException {
        //command = command.trim()
        if (command.equals("list")) {
            return Executor.list(taskList);
        } else if (command.equals("bye")) {
            return Executor.exit(taskList, storage);
        } else if (command.equals("empty")) {
            return Executor.empty(taskList);
        }
        String type;
        String desc;
        String time;
        if (command.contains(" ")) {
            type = command.substring(0, command.indexOf(" "));
            desc = command.substring(command.indexOf(" ") + 1);
        } else {
            if (!acceptedCommands.contains(command)) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } else {
                throw new DukeException("Missing argument(s)!");
            }
        }

        if (type.equals("todo")) {
            return Executor.add(taskList, type, desc);
        } else if (type.equals("deadline")) {
            if (!desc.contains("/")) {
                throw new DukeException("Missing argument(s)!");
            } else {
                time = desc.substring(desc.indexOf("/by") + 4, desc.length());
                desc = desc.substring(0, desc.indexOf("/by") - 1);
            }
            return Executor.add(taskList, type, desc, time);
        } else if (type.equals("event")) {
            if (!desc.contains("/")) {
                throw new DukeException("Missing argument(s)!");
            } else {
                time = desc.substring(desc.indexOf("/at") + 4, desc.length());
                desc = desc.substring(0, desc.indexOf("/at") - 1);
            }
            return Executor.add(taskList, type, desc, time);
        } else if (type.equals("done")) {
            return Executor.markDone(taskList, Integer.parseInt(desc) - 1);
        } else if (type.equals("delete")) {
            return Executor.delete(taskList, Integer.parseInt(desc) - 1);
        } else if (type.equals("find")) {
            return Executor.find(taskList, desc.split(" "));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
