package blarb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * {@code Tasklist} stores and operates on the list of {@code Tasks}.
 *
 * @see java.lang.Iterable
 */
class Tasklist implements Iterable<Task> {
    private final List<Task> list;

    /**
     * Initializes a {@code Tasklist}.
     */
    public Tasklist() {
        list = new ArrayList<>(100);
    }

    /**
     * Finds and prints the relevant tasks by the inputted keywords.
     *
     * @param input Keywords for the search.
     */
    public String find(String input) {
        CharSequence target = input.subSequence(0, input.length());
        String result = IntStream.rangeClosed(1, list.size())
                .filter(idx -> list.get(idx - 1).getDescription().contains(target))
                .mapToObj(idx -> String.format("\n%d. %s", idx, list.get(idx - 1).toString()))
                .collect(() -> new StringBuilder("Here are the matching tasks in your list:"),
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        if (result.lines().count() <= 1) {
            return "There are no matching tasks in your list.";
        } else {
            return result;
        }
    }

    /**
     * Lists everything in the task list.
     *
     * @return String interpretation of the task list.
     */
    public String list() {
        if (list.size() < 1) {
            return "You have nothing on your list.";
        } else {
            return IntStream.rangeClosed(1, list.size())
                    .mapToObj(idx -> String.format("\n%d. %s", idx, list.get(idx - 1).toString()))
                    .collect(() -> new StringBuilder("Here are your tasks:\n"),
                            StringBuilder::append,
                            StringBuilder::append)
                    .toString();
        }
    }

    /**
     * Adds a new Task to the task list.
     *
     * @param task Task to be added.
     * @return String interpretation of added task.
     */
    public String add(Task task) {
        String addTask = "Affirmative. I've added this task:\n %s\n"
                + "Now you have %d tasks in the list.";
        list.add(task);

        return String.format(addTask, task.toString(), list.size());
    }

    /**
     * Adds multiple new Tasks to the task list.
     *
     * @param list List of tasks to be added.
     */
    public void addAll(List<Task> list) {
        this.list.addAll(list);
    }

    /**
     * Changes the indexed task to a completed state.
     *
     * @param index The index of the task.
     * @return String interpretation of finished task
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
     * @return String interpretation of deleted task.
     * @throws IndexOutOfBoundsException The inputted index does not exist in the list.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        String delete = "The task is terminated:\n%s";
        Task output = list.remove(index);
        return String.format(delete, output);
    }

    public String update(int index, String description) throws IndexOutOfBoundsException {
        String update = "The task is updated from\n%s\nto\n%s";
        String in = list.get(index).toString();
        list.get(index).update(description);
        String out = list.get(index).toString();
        return String.format(update, in, out);
    }

    /**
     * A iteration of all the tasks in the task list.
     *
     * @return A iterator of the task list.
     */
    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
