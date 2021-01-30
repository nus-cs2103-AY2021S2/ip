package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

/**
 * ListCommand class that handles listing out the tasks in the TaskList
 * and a child of the Command class.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand object.
     * @param details Details of ListCommand object.
     */
    public ListCommand(String[] details) {
        super(details);
    }

    /**
     * Lists all the tasks in the TaskList or only the tasks of the type requested by the user.
     * @param taskList TaskList associated with the ListCommand being executed.
     * @param ui Ui associated with the ListCommand being executed.
     * @param storage Storage associated with the ListCommand being executed.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (details.length == 1) {
            System.out.println("Here are all the tasks in your list\n" + taskList);
        } else {
            switch(details[1].replaceAll("\\s", "")) {
            case "-d":
                System.out.println("These are the deadlines in your list:\n"
                        + taskList.filterByTask("Deadline"));
                break;
            case "-e":
                System.out.println("These are the events in your list:\n"
                        + taskList.filterByTask("Event"));
                break;
            case "-t":
                System.out.println("These are the todos in your list:\n"
                        + taskList.filterByTask("ToDo"));
                break;
            default:
                System.out.println("Bruh! Only use -d, -e, -t to get the specific type"
                        + "of task you want!");
            }
        }
    }

}
