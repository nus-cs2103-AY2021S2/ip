package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

/**
 * Represents a 'find' (i.e., task search) command.
 */
public class FindCommand extends Command {
    private String keywords;
    private TaskList resultList;

    /**
     * Constructs a new 'find' command.
     *
     * @param   args    the 'find' command arguments
     *                  (i.e., the keywords to use for searching the task list).
     */
    public FindCommand(String... args) {
        super(CommandType.FIND, args);
    }


    /**
     * Executes the 'find' command.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to search for tasks in using the keywords.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful execution of the 'find' command,
     *                          including the results of the search query (if any).
     * @throws VergilException  if there is a clash between any two timed tasks in the resulting list.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        keywords = getArgument(0);

        resultList = taskList.find(keywords);

        if (resultList.getLength() > 0) {
            return ui.getFoundMessage(resultList);
        } else {
            return ui.getNotFoundMessage();
        }
    }
}
