import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public Task find(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void findWithKeyword(String keyword) {
        int numberOfMatches = 0;
        int tracker = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            boolean isPresent = task.toLowerCase().contains(keyword.toLowerCase());
            if (isPresent) {
                if (tracker == 0) {
                    Ui.responseToFind();
                    tracker++;
                }
                System.out.println(task);
                numberOfMatches++;
            }
        }
        if (numberOfMatches == 0) {
            Ui.responseToNoMatches();
        }
    }

    public int getSize() {
        return tasks.size();
    }

}
