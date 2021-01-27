import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> data;

    public TaskList(ArrayList<Task> xs) {
        data = xs;
    }

    public void addTodo(String name) throws DukeInvalidArgumentException {
        if (name.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Todo(name);
        data.add(t);
    }

    public void addDeadline(String name, String timestamp) throws DukeInvalidArgumentException {
        if (name.length() * timestamp.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Deadline(name, timestamp);
        data.add(t);
    }

    public void addEvent(String name, String timestamp) throws DukeInvalidArgumentException {
        if (name.length() * timestamp.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Event(name, timestamp);
        data.add(t);
    }

    public void completeTask(int id) throws DukeInvalidArgumentException {
        if (id > data.size()) throw new DukeInvalidArgumentException();
        Task t = data.get(id - 1);
        t.markAsDone();
    }

    public void deleteTask(int id) throws DukeInvalidArgumentException {
        if (id > data.size()) throw new DukeInvalidArgumentException();
        data.remove(id - 1);
    }

    public void showTasks() {
        if (data.size() > 0) {
            for (int i = 1; i <= data.size(); i++) {
                System.out.println("   " + i + ": " + data.get(i - 1));
            }
        } else {
            System.out.println("   Looks like you haven't added any tasks.");
        }
    }
}
