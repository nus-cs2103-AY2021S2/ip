package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeTaskException;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public TaskManager() {

    }

    public ToDo addToDo(String desc) throws DukeTaskException {
        if(desc.length() == 0) {
            throw new DukeTaskException("The description of a ToDo cannot be empty.");
        } else {
            ToDo toDo = new ToDo(desc);
            tasks.add(toDo);
            return toDo;
        }
    }

    public Deadline addDeadline(String desc, LocalDateTime dateTime) throws DukeTaskException {
        if(desc.length() == 0) {
            throw new DukeTaskException("The description of a Deadline cannot be empty.");
        } else {
            Deadline deadline = new Deadline(desc, dateTime);
            tasks.add(deadline);
            return deadline;
        }
    }

    public Event addEvent(String desc, LocalDateTime start, LocalDateTime end) throws DukeTaskException {
        if(desc.length() == 0) {
            throw new DukeTaskException("The description of an Event cannot be empty.");
        } else {
            Event event = new Event(desc, start, end);
            tasks.add(event);
            return event;
        }
    }

    public Task deleteTask(int index) throws DukeTaskException {
        if(tasks.size() == 0){
            throw new DukeTaskException("There are no task to be deleted.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeTaskException("Please enter a valid task index ranging from 1 to " + tasks.size() +
                    " (inclusive).");
        } else {
            return tasks.remove(index);
        }
    }

    public void completeTask(int index) throws DukeTaskException {
        if(tasks.size() == 0){
            throw new DukeTaskException("There are no task to be completed.");
        } else if(index < 0 || index >= tasks.size()) {
            throw new DukeTaskException("Please enter a valid task index ranging from 1 to " + tasks.size() +
                    " (inclusive).");
        } else {
            Task task = tasks.get(index);
            task.markAsDone();
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTasksSize() {
        return this.tasks.size();
    }
}
