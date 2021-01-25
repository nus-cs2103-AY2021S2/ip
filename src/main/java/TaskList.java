import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    private ArrayList<Task> list;
    private Storage storage;

    private TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    public static TaskList createFromStorage(Storage storage) {
        ArrayList<Task> list = new ArrayList<>();
        List<String> lines = storage.getLines();
        for (String line : lines) {
            try {
                Task task = parseLine(line);
                list.add(task);
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
        return new TaskList(list, storage);
    }

    private static Task parseLine(String line) throws DukeException {
        String[] parts = line.split("\\|");
        System.out.println(Arrays.toString(parts));
        String type = parts[0];
        boolean isDone = Boolean.valueOf(parts[1]);
        String desc = parts[2];
        switch (type) {
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

    public void update() {
        storage.writeLines(toStorageLines());
    }

    public List<String> toStorageLines() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            lines.add(list.get(i).toFileString());
        }
        return lines;
    }

    public Task get(int index) {
        Task selected = list.get(index);
        return selected;
    }

    public int size() {
        return list.size();
    }

    public Task markAsDone(int itemNo) {
        Task selected = list.get(itemNo);
        selected.markAsDone();
        update();
        return selected;
    }

    public Task delete(int itemNo) {
        Task selected = list.get(itemNo);
        list.remove(itemNo);
        update();
        return selected;
    }

    public void add(Task task) {
        this.list.add(task);
        update();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return output;
    }

}