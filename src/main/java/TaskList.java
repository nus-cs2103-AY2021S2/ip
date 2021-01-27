import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static final String LINE_AFTER_COMMAND = "____________________________________________________________";

    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(List<Task> tasks) {
        List<Task> newTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            newTasks.add(task);
        }
        this.tasks = newTasks;
    }

    public void iterateList() {
        if (this.tasks.size() == 0) {
            Ui.showMessage("There are no task in your list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Ui.showMessageInALine(String.valueOf(i + 1) + "." + this.tasks.get(i));
        }
        Ui.printLine();
    }

    public void finishATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.set(index, this.tasks.get(index).finishTask());
            Ui.finishTaskMessage(this.tasks.get(index).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of done exceed the tasks' list.");
        }
    }

    public void deleteATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task deleted = this.tasks.get(index);
            this.tasks.remove(index);
            Ui.deleteTaskMessage(deleted.toString(), this.tasks.size());
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of delete exceed the tasks' list.");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Ui.addTaskMessage(task.toString(), this.tasks.size());
    }

    public void findTasks(String keyword) {
        List<Task> temp = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.toString().contains(keyword)) {
                temp.add(task);
            }
        }
        if (temp.size() == 0) {
            Ui.showMessage("No matching task");
            return;
        }
        Ui.showMessageInALine("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.size(); i++) {
            Ui.showMessageInALine(String.valueOf(i + 1) + "." + temp.get(i));
        }
        Ui.printLine();

    }

    public List<Task> getTasks() {
        return this.tasks;
    }


}

