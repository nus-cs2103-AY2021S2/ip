package dukeproject;

import java.util.ArrayList;

/**
 * Represents a list of task.
 * A Task List object contains a list of task object which supports methods for
 * adding, removing and getting the task and size of the task list.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /** Constructor to create an empty task list. */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to create a pre-filled task list.
     *
     * @param contents List of task represented as a string.
     * @throws DukeException If unable to determine the task type.
     */
    TaskList(ArrayList<String> contents) throws DukeException {
        this.taskList = new ArrayList<>();

        for (String task: contents) {

            // End when there is no task to work with
            if (task.isEmpty()) {
                break;
            }

            char taskType = task.charAt(1);
            char isDone = task.charAt(4);
            String description;

            // Create task based on event type
            if (taskType == 'T') {
                // ToDo Task
                description = task.substring(7);
                taskList.add(new ToDo(description, isDone == 'X'));
            } else if (taskType == 'D') {
                // Get the description and date from the user's input (Deadline Task)
                StringDatePair output = new Parser().parse(task, Parser.CommandType.FILE_DEADLINE);

                // Add the task to the task list
                taskList.add(new Deadline(output.getString(), output.getDate(), isDone == 'X'));
            } else if (taskType == 'E') {
                // Get the description and date from the user's input (Event Task)
                StringDatePair output = new Parser().parse(task, Parser.CommandType.FILE_EVENT);

                // Add the task to the task list
                taskList.add(new Event(output.getString(), output.getDate(), isDone == 'X'));
            } else {
                throw new DukeException();
            }
        }
    }

    /**
     * Add task to the task list.
     *
     * @param newTask New task to be added to the task list.
     */
    public void add(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Remove task from the task list based on the index.
     *
     * @param index The index of the item to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the Task based on the index given.
     *
     * @param index The index of the item to be retrieve.
     * @return The task based on the index given.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }
}
