import exceptions.DukeEmptyListException;
import exceptions.DukeNoDescriptionException;
import exceptions.DukeUnknownArgumentsException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    public TaskList(Storage storage, Ui ui) {
        tasks = storage.load();
        this.ui = ui;
    }

    public int size() {
        return tasks.size();
    }

    public void updateSave(Storage storage) {
        storage.update(tasks);
    }

    void done(String input) {
        int index = Parser.stringToIndex(input, 5);
        Task task = tasks.get(index);
        task.done();
        ui.printDoneMsg(task);
    }

    Todo createTodo(String input) throws DukeNoDescriptionException {
        input = Parser.parseTodoInput(input);
        return new Todo(input);
    }

    Deadline createDeadline(String input) throws DukeNoDescriptionException,
            DateTimeParseException {
        String description = Parser.obtainDescription(input, AddCommandType.DEADLINE);
        LocalDate deadline = Parser.obtainDate(input, AddCommandType.DEADLINE);
        return new Deadline(description, deadline);
    }

    Event createEvent(String input) throws DukeNoDescriptionException {
        String description = Parser.obtainDescription(input, AddCommandType.EVENT);
        LocalDate eventTime = Parser.obtainDate(input, AddCommandType.EVENT);
        return new Event(description, eventTime);
    }

    void deleteTask(String input) throws DukeEmptyListException {
        int index = Parser.stringToIndex(input, 7);
        if (tasks.isEmpty()) {
            throw new DukeEmptyListException();
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.printDeleteMsg(task, tasks.size());
    }

    void add(String input) throws DukeUnknownArgumentsException {
        try {
            Task task;
            AddCommandType command = Parser.inputToAddCommand(input);
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
        } catch (DukeNoDescriptionException e) {
            ui.printErrorMsg(e);
        } catch (DateTimeParseException e) {
            ui.printErrorMsg(e);
        }
    }

    void print() {
        ui.printTaskList(tasks);
    }
}
