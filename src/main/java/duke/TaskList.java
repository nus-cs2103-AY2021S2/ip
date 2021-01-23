package duke;

import java.util.List;

public class TaskList {
    List<Task> lst;
    Storage storage;

    TaskList() {
        this.storage = new Storage("duke.txt");
        this.lst = storage.read();

    }

    private void addToDo(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        lst.add(new ToDo(desc));
    }

    private void addDeadline(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        int index = desc.indexOf('/');
        lst.add(new Deadline(desc.substring(0, index - 1), desc.substring(index + 4)));
    }

    private void addEvent(String desc) throws DukeException {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        int index = desc.indexOf('/');
        lst.add(new Event(desc.substring(0, index - 1), desc.substring(index + 4)));
    }

    public void addTask(String command) {
        try {
            if (command.startsWith("todo")) {
                addToDo(command.substring(4));
            } else if (command.startsWith("deadline")) {
                addDeadline(command.substring(8));
            } else if (command.startsWith("event")) {
                addEvent(command.substring(5));
            } else {
                throw new DukeException("Oops! I have no idea.");
            }
            Ui.addTask(lst);
            storage.write(lst);
        } catch (DukeException err) {
            Ui.printException(err);
        }

    }

    public void markDone(String position) {
        int index = Integer.parseInt(position) - 1;
        lst.get(index).markDone();
        Ui.markDone(lst.get(index));
        storage.write(lst);
    }

    public void deleteTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task task = lst.remove(index);
        Ui.delete(lst, task);
        storage.write(lst);
    }

    public void listTask() {
        Ui.list(lst);
    }

}
