package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Parser;
import duke.commands.SpecificCommandType;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represent the TaskList to store all the tasks inputted by the user. Tasklist contains a Ui
 * class that show output from functions.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Construct TaskList containing an ArrayList of Task and the Ui.
     * @param storage Use to load the latest TaskList from save file.
     * @param ui Ui to be used to output the results from input.
     */
    public TaskList(Storage storage, Ui ui) {
        tasks = storage.load();
        this.ui = ui;
    }

    /**
     * Return number of tasks.
     * @return number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Update the save file in the hardware.
     * @param storage Storage class used for storage in the hardware.
     */
    public void updateSave(Storage storage) {
        storage.update(tasks);
    }

    /**
     * Mark the task based on the input as done.
     * @param input input used to get index of task to be marked as done.
     */
    public void done(String input) {
        int index = Parser.stringToIndex(input, 5);
        Task task = tasks.get(index);
        task.done();
        ui.printDoneMsg(task);
    }

    /**
     * Return a Todo Task that will be added to this TaskList.
     * @param input input used to get the description for the task.
     * @return Todo task with the description.
     * @throws DukeNoDescriptionException if the description of the todo command is empty.
     */
    Todo createTodo(String input) throws DukeNoDescriptionException {
        input = Parser.parseTodoInput(input);
        return new Todo(input);
    }

    /**
     * Return Deadline subclass of Task that will be added to this TaskList.
     * @param input input used to get the description and deadline of the Deadline task.
     * @return Deadline task with the description and deadline.
     * @throws DukeNoDescriptionException if the description of the deadline is empty.
     * @throws DateTimeParseException if the deadline is not of format: YYYY-MM-DD.
     */
    Deadline createDeadline(String input) throws DukeNoDescriptionException,
            DateTimeParseException {
        String description = Parser.obtainDescription(input, SpecificCommandType.DEADLINE);
        LocalDate deadline = Parser.obtainDate(input, SpecificCommandType.DEADLINE);
        return new Deadline(description, deadline);
    }

    /**
     * Return Event subclass of Task that will be added to this TaskList.
     * @param input input used to get the description and event time of the Event task.
     * @return Event task with description and event time.
     * @throws DukeNoDescriptionException when the description of the Event is empty.
     * @throws  DateTimeParseException when the event time is not of format: YYYY-MM-DD.
     */
    Event createEvent(String input) throws DukeNoDescriptionException {
        String description = Parser.obtainDescription(input, SpecificCommandType.EVENT);
        LocalDate eventTime = Parser.obtainDate(input, SpecificCommandType.EVENT);
        return new Event(description, eventTime);
    }

    /**
     * Delete Task from TaskList of index using input.
     * @param input input used to get index of Task to be deleted.
     * @throws DukeEmptyListException when the TaskList is empty.
     */
    public void deleteTask(String input) throws DukeEmptyListException {
        int index = Parser.stringToIndex(input, 7);
        if (tasks.isEmpty()) {
            throw new DukeEmptyListException();
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.printDeleteMsg(task, tasks.size());
    }

    /**
     * Decide whether the input is to add a new task or find tasks in the existing tasklist.
     * @param input input used to get the relevant information of Task.
     * @throws DukeUnknownArgumentsException when the input arguments for the creation of the
     *     Task is unknown.
     */
    public void run(String input) throws DukeUnknownArgumentsException {
        try {
            SpecificCommandType command = Parser.inputToSpecificCommand(input);
            switch (command) {
            case TODO:
            case EVENT:
            case DEADLINE:
                add(input, command);
                break;
            case FIND:
                find(input);
                break;
            default:
                throw new DukeUnknownArgumentsException();
            }
        } catch (DukeNoDescriptionException e) {
            ui.printErrorMsg(e);
        } catch (DateTimeParseException e) {
            ui.printErrorMsg(e);
        }
    }

    private void add(String input, SpecificCommandType command) throws DukeNoDescriptionException,
            DateTimeParseException, DukeUnknownArgumentsException {
        Task task;
        switch (command) {
        case TODO:
            task = createTodo(input);
            break;
        case DEADLINE:
            task = createDeadline(input);
            break;
        case EVENT:
            task = createEvent(input);
            break;
        default:
            throw new DukeUnknownArgumentsException();
        }
        tasks.add(task);
        ui.printAddMsg(task, tasks.size());
    }

    /**
     * Print String representation of the TaskList for the user.
     */
    public void print() {
        this.print(tasks);
    }

    /**
     * Print the task in the specified tasklist.
     * @param tasks the tasklist used for printing.
     */
    private void print(ArrayList<Task> tasks) {
        ui.printTaskList(tasks);
    }

    /**
     * Print tasks that contain input description.
     * @param input used to get the description to print the tasks with the description.
     * @throws DukeNoDescriptionException when the description given is empty.
     */
    public void find(String input) throws DukeNoDescriptionException {
        String description = Parser.parseFindInput(input);
        ArrayList<Task> selectedTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(description)) {
                selectedTask.add(task);
            }
        }
        ui.printFindMsg(selectedTask);
    }
}
