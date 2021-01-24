package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.Parser;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.commands.AddCommandType;
import duke.exceptions.DukeCorruptedStorageException;

import java.time.LocalDate;
import java.util.ArrayList;

public class StorageDecoder {

    public static ArrayList<Task> decodeSave(ArrayList<String> encodeTasks) throws DukeCorruptedStorageException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String encodedTask : encodeTasks) {
            AddCommandType command = Parser.parseCommandType(encodedTask);
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

    private static Task decodeTodo(String encodedTask) throws DukeCorruptedStorageException {
        boolean isDone = Parser.isEncodedTaskDone(encodedTask);
        String description = Parser.obtainDescription(encodedTask);
        Task t = new Todo(description);
        if (isDone) {
            t.done();
        }
        return t;
    }

    private static Task decodeTaskWithDate(AddCommandType command, String encodedTask) throws DukeCorruptedStorageException {
        boolean isDone = Parser.isEncodedTaskDone(encodedTask);
        String description = Parser.obtainDescription(encodedTask);
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
