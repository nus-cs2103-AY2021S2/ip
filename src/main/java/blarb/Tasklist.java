package blarb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Tasklist implements Iterable<Task> {
    private final List<Task> list;

    public Tasklist() {
        list = new ArrayList<>(100);
    }

    public String list() {
        if (list.size() < 1) {
            return "You have nothing on your list.";
        } else {
            int i = 1;
            StringBuilder sb = new StringBuilder("Here are your tasks:\n");
            for (Task task : list) {
                sb.append(String.format("\n%d. %s", i++, task.toString()));
            }
            return sb.substring(0);
        }
    }

    /**
     * Adds a new blarb.Task to the task list.
     *
     * @param task blarb.Task to be added.
     */
    public String add(Task task) {
        String addTask = "Affirmative. I've added this task:\n %s\n"
                + "Now you have %d tasks in the list.";
        list.add(task);

        return String.format(addTask, task.toString(), list.size());
    }

    public void addAll(List<Task> list) {
        this.list.addAll(list);
    }

    /**
     * Changes the indexed task to a completed state.
     *
     * @param index The index of the task.
     */
    public String done(int index) throws IndexOutOfBoundsException {
        list.get(index).markAsDone();
        String done = "I've marked this task as done:\n%s";
        return String.format(done, list.get(index));
    }

    /**
     * Delete the indexed task.
     *
     * @param index The index of the task.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        String delete = "The task is terminated:\n%s";
        Task output = list.get(index);
        list.remove(index);
        return String.format(delete, output);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
