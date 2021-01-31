package duke;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

import java.util.List;

public class TaskList {
    public enum Action{
        ADD,
        LIST,
        DONE,
        DELETE,
    }
    private boolean edited = false;
    private List<Task> store;
    public TaskList(List<Task> store) {
        this.store = store;
    }

    public List<Task> getRawData(){
        return this.store;
    }

    public String run(Command c) throws EmptyArgumentException, BadDateArgumentException {
        String[] args = c.run();
        String results;
        switch(c.getType()){
        case ADD:
            results = addTask(args);
            edited = true;
            break;
        case DONE:
            results = setDone(Integer.parseInt(args[0]));
            edited = true;
            break;
        case DELETE:
            results = delete(Integer.parseInt(args[0]));
            edited = true;
            break;
        case LIST:
            results = getList();
            break;
        default:
            results = "";
            break;
        }
        return results;
    }
    public void markSaved(){
        edited = false;
    }
    public boolean isEdited(){
        return this.edited;
    }
    private String addTask(String[] tokens) throws EmptyArgumentException, BadDateArgumentException {
        Task t;
        switch(tokens[0]){
        case "D":
            t = new Deadline(tokens[1], tokens[2]);
            break;
        case "E":
            t = new Event(tokens[1], tokens[2]);
            break;
        case "T":
            //Fall through
        default: //More fault tolerant
            t = new ToDos(tokens[1]);
            break;
        }
        store.add(t);
        return formatOrderedPrint(-1);
    }
    private String setDone(int doneIndex){
        Task t = store.get(doneIndex);
        t.setDone();
        return formatOrderedPrint(doneIndex);
    }
    private String delete(int deleteIndex){
        String returnValue = formatOrderedPrint(deleteIndex);
        store.remove(deleteIndex);
        return returnValue;
    }
    public String getList(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < store.size(); i++) {
            builder.append(formatOrderedPrint(i));
            builder.append('\n');
        }
        return builder.toString();
    }

    private String formatOrderedPrint(int i){
        final int size = store.size();
        while (i < 0){
            i += size;
        }
        while (i >= size){
            i -= size;
        }
        return "Entry " + (i+1) + "|" + store.get(i).toString();
    }
}
