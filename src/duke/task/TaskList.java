package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String serialized) throws TaskParseException {
        tasks = deserialize(serialized);
    }

    private static List<Task> deserialize(String serialized) throws TaskParseException {
        List<Task> ts = new ArrayList<>();

        Scanner sc = new Scanner(serialized);
        while (sc.hasNextLine()) {
            String taskSerial = sc.nextLine();

            Task t;
            if (ToDo.isToDo(taskSerial)) {
                t = ToDo.deserialize(taskSerial);
            } else if (Event.isEvent(taskSerial)) {
                t = Event.deserialize(taskSerial);
            } else if (Deadline.isDeadline(taskSerial)) {
                t = Deadline.deserialize(taskSerial);
            } else {
                throw new TaskParseException("Warning: invalid type. Aborting!");
            }

            ts.add(t);
        }

        return ts;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task getAt(int index) {
        return tasks.get(index);
    }

    public void removeAt(int index) {
        tasks.remove(index);
    }

    public String serialize() {
        StringBuilder serial = new StringBuilder();
        for (Task t : tasks) {
            serial.append(t.serialize()).append("\n");
        }
        return serial.toString();
    }

    public int taskCount() {
        return tasks.size();
    }
}
