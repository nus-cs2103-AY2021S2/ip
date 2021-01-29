import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public enum Action{
        ADD,
        LIST,
        DONE,
        DELETE,
    }
    List<Task> store;
    public TaskList(String[][] tokens) throws EmptyArgument, BadDateArgumentException {
        store = new ArrayList<>();
        for(String[] args: tokens){
            addTask(args);
        }
    }
    private String addTask(String[] tokens) throws EmptyArgument, BadDateArgumentException {
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
    public String run(Command c) throws EmptyArgument, BadDateArgumentException {
        String[] args = c.run();
        String results;
        switch(c.getType()){
        case ADD:
            results = addTask(args);
            break;
        case DONE:
            results = setDone(Integer.parseInt(args[0]));
            break;
        case DELETE:
            results = delete(Integer.parseInt(args[0]));
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
    private String setDone(int doneIndex){
        Task t = store.get(doneIndex);
        t.isDone = true;
        return formatOrderedPrint(doneIndex);
    }
    private String delete(int deleteIndex){
        String returnValue = formatOrderedPrint(deleteIndex);
        store.remove(deleteIndex);
        return returnValue;
    }
    private String getList(){
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
        return "Entry " + (i+1) + "|" + store.get(i).toString() + "\n";
    }
}
