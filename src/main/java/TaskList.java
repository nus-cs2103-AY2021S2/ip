import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Task list class stores user tasks
 */
public class TaskList {
    List<Task> tasks;

    /**
     * creates a task list object
     */
    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     *
     * @param tasks the user tasks
     */
    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     * @return num of items in the task
     */
    public int numOfItems() {
        return tasks.size();
    }

    /**
     *
     * @param command the type of command needed to be executed according to user input
     * @param ui the ui object
     * @param storage the storage object
     * @throws EmptyArgumentException empty argument exception
     * @throws BadDateException bad date exception
     * @throws InvalidCommandException invalid command exception
     * @throws IOException ioexception
     */
    public void run(Command command, Ui ui,Storage storage) throws EmptyArgumentException, BadDateException, InvalidCommandException, IOException {
        switch(command.getType()) {
            case "deadline":
                tasks.add(new Deadline(command.getDescription()));
                ui.showUiForAdd(tasks.get(tasks.size()-1),numOfItems());
                break;
            case "todo":
                tasks.add(new ToDos(command.getDescription()));
                ui.showUiForAdd(tasks.get(tasks.size()-1),numOfItems());
                break;
            case "event":
                tasks.add(new Event(command.getDescription()));
                ui.showUiForAdd(tasks.get(tasks.size()-1),numOfItems());
                break;
            case "delete" :
                Task toBeDeleted = tasks.get(command.itemNo-1);
                tasks.remove(command.itemNo-1);
                ui.showUiForDelete(toBeDeleted,this.numOfItems());
                break;
            case "done" :
                tasks.get(command.itemNo-1).isCompleted = true;
                ui.showUiForDone(tasks.get(command.itemNo-1));
                break;
            case "list" :
                ui.printList(tasks);
                break;
            default : throw new InvalidCommandException();
        }
        storage.save(tasks);
    }
}