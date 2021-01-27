import java.util.ArrayList;

/** Describes a wrapper to perform on Task objects. */
public class TaskList {
    public ArrayList<Task> data;

    /**
     * Returns a TaskList object.
     *
     * @param xs The ArrayList of tasks
     */
    public TaskList(ArrayList<Task> xs) {
        data = xs;
    }

    /**
     * Adds Todo objects to the task list.
     *
     * @param name The name of the todo
     * @throws DukeInvalidArgumentException
     */
    public void addTodo(String name) throws DukeInvalidArgumentException {
        if (name.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Todo(name);
        data.add(t);
    }

    /**
     * Adds Deadline objects to the task list.
     *
     * @param name The name of the deadline
     * @param timestamp The string representation of the timestamp
     * @throws DukeInvalidArgumentException
     */
    public void addDeadline(String name, String timestamp) throws DukeInvalidArgumentException {
        if (name.length() * timestamp.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Deadline(name, timestamp);
        data.add(t);
    }

    /**
     * Adds Event objects to the task list.
     *
     * @param name The name of the event
     * @param timestamp The string representation of the timestamp
     * @throws DukeInvalidArgumentException
     */
    public void addEvent(String name, String timestamp) throws DukeInvalidArgumentException {
        if (name.length() * timestamp.length() == 0) {
            throw new DukeInvalidArgumentException();
        }

        Task t = new Event(name, timestamp);
        data.add(t);
    }

    /**
     * Marks a task as complete.
     *
     * @param id The id of the task in the task list
     * @throws DukeInvalidArgumentException
     */
    public void completeTask(int id) throws DukeInvalidArgumentException {
        if (id > data.size()) throw new DukeInvalidArgumentException();
        Task t = data.get(id - 1);
        t.markAsDone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param id The id of the task in the task list
     * @throws DukeInvalidArgumentException
     */
    public void deleteTask(int id) throws DukeInvalidArgumentException {
        if (id > data.size()) throw new DukeInvalidArgumentException();
        data.remove(id - 1);
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void findTasks(String keyword) {
        if (data.size() > 0){
            for (int i = 1; i <= data.size(); i++) {
                Task t = data.get(i - 1);
                if (t.name.contains(keyword)) {
                    System.out.println("   " + i + ": " + t);
                }
            }
        } else {
            System.out.println("   Looks like you haven't added any tasks.");
        }
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
