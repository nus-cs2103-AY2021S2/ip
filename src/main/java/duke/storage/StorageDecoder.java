package duke.storage;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.Parser;
import duke.commands.SpecificCommandType;
import duke.exceptions.DukeCorruptedStorageException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents the StorageDecoder that decodes the data representation of the save file into the
 * ArrayList of Task.
 */
public class StorageDecoder {

    /**
     * Returns ArrayList of Task decoded from the save file.
     * @param encodeTasks data representation of the TaskList from save file.
     * @return ArrayList of Task from the encodeTasks.
     * @throws DukeCorruptedStorageException when the encodedTasks does not conform with the
     *     proper save file format.
     */
    public static ArrayList<Task> decodeSave(ArrayList<String> encodeTasks) throws DukeCorruptedStorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String encodedTask : encodeTasks) {
            SpecificCommandType command = Parser.parseCommandType(encodedTask);
            Task task;
            switch (command) {
            case TODO:
                task = decodeTodo(encodedTask);
                break;
            case EVENT:
            case DEADLINE:
                task = decodeTaskWithDate(command, encodedTask);
                break;
            default:
                throw new DukeCorruptedStorageException();
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Returns Todo Task based on a single line of the encodedTask.
     * @param encodedTask data representation of a single Todo Task from save file.
     * @return Todo Task based on the encodedTask.
     * @throws DukeCorruptedStorageException when the Task does not fit the format of a Todo Task.
     */
    private static Task decodeTodo(String encodedTask) throws DukeCorruptedStorageException {
        assert(!encodedTask.isEmpty());
        boolean isDone = Parser.isEncodedTaskDone(encodedTask);
        String description = Parser.obtainEncodedDescription(encodedTask);
        Task t = new Todo(description);
        if (isDone) {
            t.done();
        }
        return t;
    }

    /**
     * Returns either Event or Deadline Task based on the single line encodedTask, based on
     * AddCommandType.
     * @param command AddCommandType used to distinguish between Event and Deadline.
     * @param encodedTask data representation of the Event or Dateline from save file.
     * @return Event or Deadline based on the command using encodedTask.
     * @throws DukeCorruptedStorageException when the encodedTask does not fit the format for a
     *     Task with dates.
     */
    private static Task decodeTaskWithDate(SpecificCommandType command, String encodedTask)
            throws DukeCorruptedStorageException {
        assert(command != null);
        assert(!encodedTask.isEmpty());
        boolean isDone = Parser.isEncodedTaskDone(encodedTask);
        String description = Parser.obtainEncodedDescription(encodedTask);
        LocalDate date = Parser.obtainEncodedDate(encodedTask);
        Task t;
        switch (command) {
        case EVENT:
            t = new Event(description, date);
            break;
        case DEADLINE:
            t = new Deadline(description, date);
            break;
        default:
            throw new DukeCorruptedStorageException();
        }
        if (isDone) {
            t.done();
        }
        return t;
    }
}
