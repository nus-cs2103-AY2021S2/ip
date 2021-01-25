import java.util.List;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
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
        return selected;
    }

    public Task delete(int itemNo) {
        Task selected = list.get(itemNo);
        list.remove(itemNo);
        return selected;
    }

    public void add(Task task) {
        this.list.add(task);
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