package duke.tasks;

import duke.Parser;
import duke.commands.SpecificCommandType;
import duke.exceptions.DukeEmptyListException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeUnknownArgumentsException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final Ui ui;

    public TaskList(Storage storage, Ui ui) {
        taskList = storage.load();
        this.ui = ui;
    }

    public int size() {
        return taskList.size();
    }

    public void updateSave(Storage storage) {
        storage.update(taskList);
    }

    public void done(String input) {
        int index = Parser.stringToIndex(input, 5);
        Task task = taskList.get(index);
        task.done();
        ui.printDoneMsg(task);
    }

    Todo createTodo(String input) throws DukeNoDescriptionException {
        input = Parser.parseTodoInput(input);
        return new Todo(input);
    }

    Deadline createDeadline(String input) throws DukeNoDescriptionException,
            DateTimeParseException {
        String description = Parser.obtainDescription(input, SpecificCommandType.DEADLINE);
        LocalDate deadline = Parser.obtainDate(input, SpecificCommandType.DEADLINE);
        return new Deadline(description, deadline);
    }

    Event createEvent(String input) throws DukeNoDescriptionException {
        String description = Parser.obtainDescription(input, SpecificCommandType.EVENT);
        LocalDate eventTime = Parser.obtainDate(input, SpecificCommandType.EVENT);
        return new Event(description, eventTime);
    }

    public void deleteTask(String input) throws DukeEmptyListException {
        int index = Parser.stringToIndex(input, 7);
        if (taskList.isEmpty()) {
            throw new DukeEmptyListException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.printDeleteMsg(task, taskList.size());
    }

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
        taskList.add(task);
        ui.printAddMsg(task, taskList.size());
    }

    public void print() {
        this.print(taskList);
    }

    private void print(ArrayList<Task> tasks) {
        ui.printTaskList(tasks);
    }

    public void find(String input) throws DukeNoDescriptionException {
        String description = Parser.parseFindInput(input);
        ArrayList<Task> selectedTask = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(description)) {
                selectedTask.add(task);
            }
        }
        ui.printFindMsg(selectedTask);
    }
}
