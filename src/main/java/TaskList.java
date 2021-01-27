import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task taskToAdd) {
        this.list.add(taskToAdd);
    }

    public void removeTask(int i) {
        this.list.remove(i-1);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int i) {
        return this.list.get(i - 1);
    }

    public String joinToTxt() {
        String joined = "";
        for (Task t : this.list) {
            joined += System.lineSeparator() + t.saveTask();
        }
        return joined;
    }

    /**
     * Returns list of all events.
     */
    @Override
    public String toString() {
        String stringToReturn = "";
        for (int i=1; i<=this.getSize(); i++) {
            if (i==this.getSize()) {
                stringToReturn += "  " + i + ". " + this.getTask(i);
            } else {
                stringToReturn += "  " + i + ". " + this.getTask(i) + "\n";
            }
        }
        return stringToReturn;
    }

}
