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
     * @param storage the storage object
     * @throws EmptyArgumentException empty argument exception
     * @throws BadDateException bad date exception
     * @throws InvalidCommandException invalid command exception
     * @throws IOException ioexception
     */
    public String run(Command command, Storage storage)  {
        try {
        switch(command.getType()) {
            case "deadline":
                tasks.add(new Deadline(command.getDescription()));
                return "added: " + tasks.get(tasks.size()-1) + "\n" + "Now you have " + numOfItems() + " tasks in the list";
            case "todo":
                tasks.add(new ToDos(command.getDescription()));
                return "added: " + tasks.get(tasks.size()-1) + "\n" + "Now you have " + numOfItems() + " tasks in the list";
            case "event":
                tasks.add(new Event(command.getDescription()));
                return "added: " + tasks.get(tasks.size()-1) + "\n" + "Now you have " + numOfItems() + " tasks in the list";
            case "delete" :
                Task toBeDeleted = tasks.get(command.itemNo-1);
                tasks.remove(command.itemNo-1);
                return "Noted. I have removed this task: " + toBeDeleted + "\n" + "Now you have " + numOfItems() + " tasks in the list";
            case "done" :
                tasks.get(command.itemNo-1).isCompleted = true;
                return "Nice, I have marked this task as done!\n" + tasks.get(command.itemNo-1);
            case "list" :
                String res = "here are your tasks:\n";
                for(int itemNo = 1;itemNo <= tasks.size();itemNo++) {
                    res += " ";
                    res += "" + itemNo + ". " + tasks.get(itemNo-1) + "\n";
                }
                return res;
            default : return "Sorry, invalid command!";
        }
        }
        catch(EmptyArgumentException | BadDateException e) {
            return e.getMessage();
        }
    }
}