import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    void deleteTask(int taskIndex) throws InvalidDescriptionException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.remove(taskIndex);
    }

    void markTask(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.get(taskIndex).markAsDone();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finds <code>Task</code> in the ArrayList that contains the matching word
     * @param keyWord indicates the word to find
     * @return an ArrayList that contains the <code>Tasks</code> that has the keyword
     */
    public ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> matchingWords = new ArrayList<>();

        for (Task t : tasks) {
            if (t.toString().contains(keyWord)) {
                matchingWords.add(t);
            }
        }

        return matchingWords;
    }
}
