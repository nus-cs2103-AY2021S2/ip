import java.util.ArrayList;
import java.util.List;

public class TaskList {

    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public static TaskList createWithFileStrings(List<String> list) {
        TaskList tl =  new TaskList();
        for(String s : list) {
            try {
                Task task = parseFileString(s);
                tl.add(task);
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
        return tl;
    }

    private static Task parseFileString(String fileString) throws DukeException {
        String[] parts = fileString.split("|");
        String type = parts[0];
        boolean isDone = Boolean.valueOf(parts[1]);
        String desc = parts[2];
        switch(type) {
        case "T":
            return new Todo(desc, isDone);
        case "D":
            return new Deadline(desc, isDone, parts[3]);
        case "E":
            return new Event(desc, isDone, parts[3]);
        default:
            throw new DukeException("String parsing failed");
        }
    }

    private void saveToStorage() {
    }

    public void markAsDone(int itemNo) {
        Task selected = list.get(itemNo - 1);
        selected.markAsDone();
    }

    public void delete(int itemNo) {
        list.remove(itemNo - 1);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < list.size(); i++) {
            output += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return output;
    }

}