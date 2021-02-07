package duke.command;

import java.util.HashMap;

import duke.task.TaskList;

/**
 * Represents the execution of Help Command
 */
public class HelpCmd extends Command {
    private static final String TITLE = "Usage: <command> [other arguments]\n\n"
            + "Commands:\n";

    private static final HashMap<String, String> commands = new HashMap<>();

    static {
        commands.put("help", "Show this page");
        commands.put("todo <content>", "Add a todo");
        commands.put("event <content> /at <place/other details>", "Add an event that happens at a specific place");
        commands.put("deadline <content> /by <datetime>", "Add a task with a specific deadline. Allowed datetime "
                + "formats: [02/01/2021 1220, 02/01/2021]");
        commands.put("delete <list index>", "Delete task with specified index from list command");
        commands.put("done <list index>", "Mark task with specific index from list command as done");
        commands.put("find <search string>", "List tasks with specified search string found in their details");
        commands.put("bye", "Quit the bot");
    }

    private String createCommandHelpText(String commandFormat, String commandInfo) {
        String firstLine = String.format("\t%s\n", commandFormat);
        String secondLine = String.format("\t\t%s\n", commandInfo);
        return firstLine + secondLine + "\n";
    }

    @Override
    public String execute(TaskList lst) {
        StringBuilder sb = new StringBuilder();
        sb.append(TITLE);

        commands.forEach((cmdFmt, cmdInfo) -> {
            sb.append(createCommandHelpText(cmdFmt, cmdInfo));
        });

        return sb.toString();
    }
}
