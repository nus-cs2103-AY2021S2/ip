package duke;

import java.util.List;
import java.util.stream.IntStream;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.BadIndexException;
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
     * Runs command on TaskList and returns command specific output.
     * Side effects are present on some commands
     *
     * @param c Command to be run
     * @return Output meant for Ui Class
     * @throws EmptyArgumentException At least one argument is missing
     * @throws BadDateArgumentException An argument that is expected to be a date is ill formatted
     * @throws InvalidCommandException A bad command has been passed that cannot be handled
     * @throws BadIndexException An index in the command is out of bounds and needs to be communicated
     */
    public String run(Command c)
            throws EmptyArgumentException, BadDateArgumentException,
            InvalidCommandException, BadIndexException {
        String[] args = c.getCommandParameters();
        String result;
        Action action = c.getType();
        switch (action) {
        case ADD:
            result = addTask(args);
            edited = true;
            break;
        case DONE:
            checkIndex(Integer.parseInt(args[0]));
            result = setDone(Integer.parseInt(args[0]));
            edited = true;
            break;
        case DELETE:
            checkIndex(Integer.parseInt(args[0]));
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
            assert tokens.length == 3;
            t = new Deadline(task, additional);
            break;
        case "E":
            assert tokens.length == 3;
            t = new Event(task, additional);
            break;
        case "T":
            assert tokens.length == 2;
            t = new ToDos(task);
            break;
        default:
            assert false
                    : "This assertion failed because an un-processable command has been received";
            throw new InvalidCommandException("of type " + type);
        }
        store.add(t);
    }
    private void checkIndex(int index) throws BadIndexException {
        if (index < 0 || index >= store.size()) {
            throw new BadIndexException(index);
        }
    }
    private String setDone(int doneIndex) {
        assert doneIndex >= 0 && doneIndex < store.size();
        Task t = store.get(doneIndex);
        t.setDone();
        return formatOrderedPrint(doneIndex);
    }
    private String delete(int deleteIndex) {
        assert deleteIndex >= 0 && deleteIndex < store.size();
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
        return IntStream.range(0, store.size())
                        .parallel()
                        .mapToObj(this::formatOrderedPrint)
                        .reduce("", (a, b) -> a + b + "\n");
    }

    private String getFilteredList(String searchTerm) {
        return IntStream.range(0, store.size())
                        .parallel()
                        .filter(i -> store.get(i).containsSearch(searchTerm))
                        .mapToObj(this::formatOrderedPrint)
                        .reduce("", (a, b) -> a + b + "\n");
    }
    private String formatOrderedPrint(int i) {
        return "Entry " + (i + 1) + "|" + store.get(i).toString();
    }

    /**
     * Generates a string that represents the state of TaskList,
     * such that the program can recreate it.
     *
     * @return A machine interpretable representation of TaskList
     */
    public String toFileString() {
        StringBuilder saveText = new StringBuilder();
        for (Task t: store) {
            saveText.append(t.toFileString());
            saveText.append('\n');
        }
        return saveText.toString();
    }
}
