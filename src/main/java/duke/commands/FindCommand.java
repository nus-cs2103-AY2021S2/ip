package duke.commands;

import java.util.ArrayList;

import duke.DukeException;
import duke.ParserUtils;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Handles the find command of the user to search tasks based on keyword.
 * Format of command: "find [keyword]".
 */
public class FindCommand implements Command {
    public static final String TODO_KEYWORD = "todo";
    public static final String EVENT_KEYWORD = "event";
    public static final String DEADLINE_KEYWORD = "deadline";

    private final String keyword;

    protected FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a new instance of Find Command
     *
     * @param argString string with argument
     * @return instance of Find Command
     * @throws DukeException
     */
    public static FindCommand buildInstance(String argString) throws DukeException {
        String[] cmdArgs = ParserUtils.getCommandArgs(argString, "I'm sorry, but find needs a keyword specified.");

        assert (cmdArgs[0].equals("find"));

        String keyword = cmdArgs[1];
        return new FindCommand(keyword);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            ui.printIndentOutput("The current list is empty.");
        } else {
            printList(tasks, ui);
        }
    }

    private void printList(TaskList tasks, Ui ui) {
        ArrayList<Integer> indexList = new ArrayList<>(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getTaskName().contains(keyword)) {
                indexList.add(i);
            }
        }
        if (indexList.size() <= 0) {
            ui.printIndentOutput("No tasks found with the given keyword.");
        } else {
            ui.printIndentOutput("Here are the matching tasks in you list:");
            for (int i = 0; i < indexList.size(); i++) {
                ui.printIndentOutput((i + 1) + ". " + tasks.getTask(indexList.get(i)));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
