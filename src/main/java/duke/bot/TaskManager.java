package duke.bot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/** An object that manage the storing, retrieving and removing of tasks from the chat bot */
public class TaskManager {
    /** A list of tasks tracked by the chat bot */
    private List<Task> tasks = new ArrayList<>();

    private boolean hasToDo(String desc) {
        return tasks.stream().anyMatch(task -> (task.getTypeSymbol().equals("T") && task.getDesc().equals(desc)));
    }

    /**
     * Adds a new ToDo into the task list
     *
     * @param desc Description of the new ToDo
     * @return ToDo that was created
     * @throws DukeTaskException if description provided is empty
     */
    public ToDo addToDo(String desc) throws DukeTaskException {
        if (desc.length() == 0) {
            throw new DukeTaskException("The description of a ToDo cannot be empty.");
        } else if (hasToDo(desc)) {
            throw new DukeTaskException("This todo has already been added! Try add with a different name.");
        }

        ToDo toDo = new ToDo(desc);
        tasks.add(toDo);
        return toDo;
    }

    private boolean hasDeadline(String desc, LocalDateTime dateTime) {
        return tasks.stream().anyMatch(task -> {
            if (!task.getTypeSymbol().equals("D")) {
                return false;
            }

            Deadline deadline = (Deadline) task;
            System.out.println("Type: " + task.getTypeSymbol() + " desc: " + task.getDesc() + " equal: " + deadline.getDateTime().isEqual(dateTime));
            return deadline.getDesc().equals(desc) && deadline.getDateTime().isEqual(dateTime);
        });
    }

    /**
     * Adds a new Deadline into the task list
     *
     * @param desc Description of the new Deadline
     * @param dateTime Date and time when the new Deadline is due
     * @return Deadline that was created
     * @throws DukeTaskException if description provided is empty
     */
    public Deadline addDeadline(String desc, LocalDateTime dateTime) throws DukeTaskException {
        if (desc.length() == 0) {
            throw new DukeTaskException("The description of a Deadline cannot be empty.");
        } else if (hasDeadline(desc, dateTime)) {
            throw new DukeTaskException("This deadline has already been added! Try add with a different name.");
        }

        Deadline deadline = new Deadline(desc, dateTime);
        tasks.add(deadline);
        return deadline;
    }

    private boolean hasEvent(String desc, LocalDateTime start, LocalDateTime end) {
        return tasks.stream().anyMatch(task -> {
            if (!task.getTypeSymbol().equals("E")) {
                return false;
            }

            Event event = (Event) task;
            return event.getDesc().equals(desc) && event.getStartDateTime().isEqual(start)
                    && event.getEndDateTime().isEqual(end);
        });
    }

    /**
     * Adds a new Event into the task list
     *
     * @param desc Description of the new Event
     * @param start Date and time when the new Event starts
     * @param end Date and time when the new Event ends
     * @return Event that was created
     * @throws DukeTaskException if description provided is empty
     */
    public Event addEvent(String desc, LocalDateTime start, LocalDateTime end) throws DukeTaskException {
        if (desc.length() == 0) {
            throw new DukeTaskException("The description of an Event cannot be empty.");
        } else if (hasEvent(desc, start, end)) {
            throw new DukeTaskException("This event has already been added! Try add with a different name.");
        }

        Event event = new Event(desc, start, end);
        tasks.add(event);
        return event;
    }

    /**
     * Deletes a specific task from the task list
     *
     * @param index Index of the task that you want to delete
     * @return Deleted task
     * @throws DukeTaskException if there is no task in the list or the index is out of range
     */
    public Task deleteTask(int index) throws DukeTaskException {
        if (tasks.size() == 0) {
            throw new DukeTaskException("There are no task to be deleted.");
        } else if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskException("Please enter a valid task index ranging from 1 to " + tasks.size()
                    + " (inclusive).");
        }

        return tasks.remove(index);
    }

    /**
     * Marks a specific task in the task list as completed
     *
     * @param index Index of the task that you want to complete
     * @throws DukeTaskException if there is no task in the list or the index is out of range
     */
    public void completeTask(int index) throws DukeTaskException {
        if (tasks.size() == 0) {
            throw new DukeTaskException("There are no task to be completed.");
        } else if (index < 0 || index >= tasks.size()) {
            throw new DukeTaskException("Please enter a valid task index ranging from 1 to " + tasks.size()
                    + " (inclusive).");
        }

        tasks.get(index).markAsDone();
    }

    /**
     * Returns a task in the list with a specific index
     *
     * @param index Index of a task you want to retrieve
     * @return Task with the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns a copy of the task list
     *
     * @return A copy of the task list
     */
    public List<Task> getTasks() {
        return new ArrayList<>(this.tasks);
    }

    /**
     * Sets the content of the task list
     *
     * @param tasks A task list to import the content from
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return Number of tasks in the task list
     */
    public int getTasksSize() {
        return this.tasks.size();
    }
}
