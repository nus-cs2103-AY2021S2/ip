package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

import java.util.ArrayList;

/**
 * A subclass of the <code>Duke.Duke.Commands.Command</code>class that represents a command to find a keyword the the ArrayList
 */
public class FindCommand extends Command {

    String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command and finds the <code>Tasks</code> that matches the keyword from the <code>TaskList</code>
     * Uses <code>Duke.Ui</code> to print the <code>TaskList</code> of the matching keyword <code>Tasks</code>
     *
     * @param tasklist contains the task list and operations to manipulate the list
     * @param ui       deals with interaction with the user
     * @param storage  deals with loading tasks from a file and saving into it
     * @return String that consists of found tasks
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        ArrayList<Task> matchingArrayList = tasklist.findTask(this.keyWord);
        TaskList matchingList = new TaskList(matchingArrayList);

        return ui.printList(matchingList);
    }
}

