import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    TaskList() {
        tasks = new ArrayList<>();
    }
    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public int numOfItems() {
        return tasks.size();
    }

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