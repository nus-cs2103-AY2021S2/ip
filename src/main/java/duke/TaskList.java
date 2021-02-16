package duke;

import java.util.List;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class TaskList {
    public enum Action {
        ADD,
        LIST,
        DONE,
        DELETE,
        SEARCH,
    }
    private boolean edited = false;
    private final List<Task> store;
    public TaskList(List<Task> store) {
        this.store = store;
    }

    /**
     * Get Raw data for extraction of File ready data from Task directly
     * TODO: Push the preparation of data to TaskList
     *
     * @return Underlying data structure
     */
    public List<Task> getRawData() {
        return this.store;
    }

    /**
     * Runs command on TaskList and returns command specific output.
     * Side effects are present on some commands
     *
     * @param c Command to be run
     * @return Output meant for Ui Class
     * @throws EmptyArgumentException At least one argument is missing
     * @throws BadDateArgumentException An argument that is expected to be a date is ill formatted
     */
    public String run(Command c)
            throws EmptyArgumentException, BadDateArgumentException, InvalidCommandException {
        String[] args = c.getCommandParameters();
        String result;
        Action action = c.getType();
        switch (action) {
        case ADD:
            result = addTask(args);
            edited = true;
            break;
        case DONE:
            result = setDone(Integer.parseInt(args[0]));
            edited = true;
            break;
        case DELETE:
            result = delete(Integer.parseInt(args[0]));
            edited = true;
            break;
        case LIST:
            result = getList();
            break;
        case SEARCH:
            result = getFilteredList(args[0]);
            break;
        default:
            result = "";
            break;
        }
        return result;
    }

    /**
     * Mark the changes in the TaskList as saved to disk.
     */
    public void markSaved() {
        edited = false;
    }

    /**
     * Check whether TaskList has been edited from when it has been last saved to disk
     *
     * @return Whether the TaskList has changed
     */
    public boolean isEdited() {
        return this.edited;
    }
    private String addTask(String[] tokens)
            throws EmptyArgumentException, BadDateArgumentException, InvalidCommandException {
        generateTask(tokens);
        int lastIndex = store.size() - 1;
        return formatOrderedPrint(lastIndex);
    }
    private void generateTask(String[] tokens)
            throws EmptyArgumentException, BadDateArgumentException, InvalidCommandException {
        String type = tokens[0];
        String task = tokens[1];
        String additional = tokens.length >= 3 ? tokens[2] : null;
        Task t;
        switch (type) {
        case "D":
            t = new Deadline(task, additional);
            break;
        case "E":
            t = new Event(task, additional);
            break;
        case "T":
            t = new ToDos(task);
            break;
        default:
            //TODO: Add assert false here later when merging
            throw new InvalidCommandException("of type " + type);
        }
        store.add(t);
    }
    private String setDone(int doneIndex) {
        Task t = store.get(doneIndex);
        t.setDone();
        return formatOrderedPrint(doneIndex);
    }
    private String delete(int deleteIndex) {
        String returnValue = formatOrderedPrint(deleteIndex);
        store.remove(deleteIndex);
        return returnValue;
    }

    /**
     * Gets a user friendly list of all the task in TaskList
     *
     * @return User friendly state of TaskList
     */
    public String getList() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < store.size(); i++) {
            builder.append(formatOrderedPrint(i));
            builder.append('\n');
        }
        return builder.toString();
    }

    private String getFilteredList(String searchTerm) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).containsSearch(searchTerm)) {
                builder.append(formatOrderedPrint(i));
                builder.append('\n');
            }
        }
        return builder.toString();
    }
    private String formatOrderedPrint(int i) {
        return "Entry " + (i + 1) + "|" + store.get(i).toString();
    }
}
