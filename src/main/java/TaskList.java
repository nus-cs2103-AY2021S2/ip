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
        if (!hasDuplicates(t)) data.add(t);
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
        if (!hasDuplicates(t)) data.add(t);
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
        if (!hasDuplicates(t)) data.add(t);
    }

    public boolean hasDuplicates(Task t) {
        for (var d : data) {
            if (d.toText().equals(t.toText())) return true;
        }

        return false;
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
     * Prints all the tasks in the task list matching the search keyword.
     */
    public String findTasks(String keyword) {
        String response = "Sonia: Here are your tasks!\n";
        if (data.size() > 0){
            for (int i = 1; i <= data.size(); i++) {
                Task t = data.get(i - 1);
                if (t.name.contains(keyword)) {
                    response += "   " + i + ": " + t + "\n";
                }
            }
        } else {
            response = "   Looks like you haven't added any tasks.";
        }

        return response.substring(0, response.length() - 1);
    }

    /**
     * Prints all the tasks in the task list.
     */
    public String showTasks() {
        String response = "Sonia: Here are your tasks!\n";
        if (data.size() > 0) {
            for (int i = 1; i <= data.size(); i++) {
                response += "   " + i + ": " + data.get(i - 1) + "\n";
            }
        } else {
            response = "   Looks like you haven't added any tasks.";
        }

        return response.substring(0, response.length() - 1);
    }
}
