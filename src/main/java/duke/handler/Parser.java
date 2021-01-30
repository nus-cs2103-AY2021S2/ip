package duke.handler;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {
    public static Task parseFromData(String dataInput) {
        String[] splitInput = dataInput.split("\\|", -1);
        Task task;
        switch (splitInput[0]) {
        case "T":
            task = new Todo(splitInput[2]);
            break;
        case "D":
            task = new Deadline(splitInput[2], splitInput[3]);
            break;
        case "E":
            task = new Event(splitInput[2], splitInput[3]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + splitInput[0]);
        }
        return splitInput[1].equals("0")
                ? task
                : task.markDone();
    }
}
