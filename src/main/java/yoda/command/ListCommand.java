package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

public class ListCommand extends Command {
    public ListCommand(String[] details) {
        super(details);
    }

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
