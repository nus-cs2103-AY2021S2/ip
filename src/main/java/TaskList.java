import exceptions.DukeEmptyListException;
import exceptions.DukeNoDescriptionException;
import exceptions.DukeUnknownArgumentsException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        tasks = storage.load();
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
        String output = String.format("\t" + " Nice! I've marked this task as done:" + "\n"
                + "\t" + "\t" + " %s", task);
        System.out.println(output);
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
        Task t = tasks.get(index);
        tasks.remove(index);
        String output =
                String.format("\t" + " Noted. I've removed this task:" + "\n" + "\t" + "\t"
                                + t + "\n" + "\t" + " Now you have %d tasks in the list.",
                        tasks.size());
        System.out.println(output);
    }

    void add(String input) throws DukeUnknownArgumentsException {
        try {
            Task t;
            AddCommandType command = Parser.inputToAddCommand(input);
            switch (command) {
            case TODO:
                t = createTodo(input);
                break;
            case DEADLINE:
                t = createDeadline(input);
                break;
            case EVENT:
                t = createEvent(input);
                break;
            default:
                throw new DukeUnknownArgumentsException();
            }
            tasks.add(t);
            String output = String.format("\t" + " Got it. I've added this task:" + "\n"
                            + "\t" + "\t" + " %s" + "\n" + "\t" + " Now you have %d tasks "
                            + "in the list."
                    , t, tasks.size());
            System.out.println(output);
        } catch (DukeNoDescriptionException e) {
            String output = String.format("\t" + " %s", e);
            System.out.println(output);
        } catch (DateTimeParseException e) {
            System.out.println("\t" + "Date is not input correctly. " + e.getMessage());
        }
    }

    void print() {
        System.out.println("\t" + " Here are the tasks in your list:");
        int num = 1;
        for (Task task : tasks) {
            String output = String.format("\t" + " %d.%s", num, task);
            System.out.println(output);
            num++;
        }
    }
}
