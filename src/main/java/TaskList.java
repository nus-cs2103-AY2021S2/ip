import java.util.List;
import java.util.ArrayList;

class TaskList {
    private final List<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    void add(Task task){
        list.add(task);
    }

    Task delete(String num) throws DukeException{
        int index = Integer.valueOf(num) - 1;

        if(index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        return list.remove(index);
    }

    Task markTaskAsDone(String num) throws DukeException{
        int index = Integer.valueOf(num) - 1;

        if(index < 0 || index >= list.size()) {
            throw new DukeException("Please enter an appropriate index.");
        }

        Task t = list.get(index);
        t.markAsDone();

        return t;
    }

    String listOutTaskInString() {
        String res = "";

        res += "Done tasks: " + System.lineSeparator();

        for(Task t: list) {
            if(t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        res += "Pending tasks: " + System.lineSeparator();

        for(Task t: list) {
            if(!t.getIsDone()) {
                res += t.toFileString() + System.lineSeparator();
            }
        }

        return res;
    }
}
