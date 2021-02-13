package bob.task;

import java.time.LocalDateTime;
import java.util.*;

import bob.BobException;

/**
 * Represents a list of task
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private LinkedHashMap<LocalDateTime, ArrayList<Task>> reminders;
    private Task addedReminder;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.reminders = new LinkedHashMap<>();
    }

    /**
     * Constructor of TaskList
     *
     * @param taskList A list of task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.reminders = new LinkedHashMap<>();
    }

    public TaskList(ArrayList<Task> taskList, LinkedHashMap<LocalDateTime, ArrayList<Task>> reminders) {
        this.taskList = taskList;
        this.reminders = reminders;
        sortReminders();
    }

    public void sortReminders() {
        LinkedHashMap<LocalDateTime, ArrayList<Task>> duplicateList = new LinkedHashMap<>(this.reminders);
        List<Map.Entry<LocalDateTime, ArrayList<Task>>> tasksToRemind = new ArrayList<>(duplicateList.entrySet());
        this.reminders.clear();
        tasksToRemind.sort(new Comparator<Map.Entry<LocalDateTime, ArrayList<Task>>>() {
            @Override
            public int compare(Map.Entry<LocalDateTime, ArrayList<Task>> t1
                    , Map.Entry<LocalDateTime, ArrayList<Task>> t2) {
                return t1.getKey().compareTo(t2.getKey());
            }
        });

        for (Map.Entry<LocalDateTime, ArrayList<Task>> entry : tasksToRemind) {
            this.reminders.put(entry.getKey(), entry.getValue());
        }
    }

    public void addReminder(LocalDateTime remindTime, Task task) {
        if (this.reminders.containsKey(remindTime)) {
            this.reminders.get(remindTime).add(task);
        } else {
            ArrayList<Task> listOfTasks = new ArrayList<>();
            listOfTasks.add(task);
            this.reminders.putIfAbsent(remindTime, listOfTasks);
        }
        this.addedReminder = task;
        sortReminders();
    }

    public Task getTaskWithReminder() {
        return this.addedReminder;
    }

    public void removeReminder(Task taskToBeRemoved) {
        for (ArrayList<Task> listOfTasks : this.reminders.values()) {
            listOfTasks.removeIf(task -> task.equals(taskToBeRemoved));
            if (listOfTasks.size() == 0) {
                this.reminders.remove(taskToBeRemoved.reminderDateTime);
                break;
            }
        }
    }

    public void removeReminder(LocalDateTime remindTime, Task task) {
        if (this.reminders.containsKey(remindTime)) {
            this.reminders.get(remindTime).remove(task);
            if (this.reminders.get(remindTime).size() == 0) {
                this.reminders.remove(remindTime);
            }
        }
    }

    public LinkedHashMap<LocalDateTime, ArrayList<Task>> getReminders() {
        return this.reminders;
    }

    /**
     * Update the status of one of the tasks in the list
     *
     * @param index The index of the task to be updated
     * @param isDone The status of the task to be updated
     * @return An updated Task object containing the changed task
     */
    public Task changeStatus(int index, boolean isDone) throws BobException {
        try {
            Task updatedTask = this.taskList.get(index);
            updatedTask.setStatus(isDone);
            if (isDone) {
                removeReminder(updatedTask);
            }
            return updatedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Please try again with a valid task index!", e);
        }
    }

    /**
     * Returns the list of tasks
     *
     * @return ArrayList of the tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the total number of tasks in the list
     *
     * @return The size of the ArrayList
     */
    public int getSize() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a todo task to the list
     *
     * @param task Todo task to be added
     */
    public void addToDo(Todo task) {
        taskList.add(task);
    }

    /**
     * Adds an event to the list
     *
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        taskList.add(task);
    }

    /**
     * Add a deadline to the list
     *
     * @param task The deadline to be added
     */
    public void addDeadline(Deadline task) {
        taskList.add(task);
    }

    /**
     * Remove a task from the list
     *
     * @param index The index of the task to be removed
     * @return The updated Task object with the task removed
     */
    public Task removeTask(int index) {
        Task task = taskList.remove(index);
        removeReminder(task);
        return task;
    }

    /**
     * Prints the whole list of tasks
     *
     * @return A string representing the Task
     */
    @Override
    public String toString() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            tasks.append(index).append(".").append(taskList.get(i)).append("\n");
        }

        tasks.append("Upcoming reminders: \n");

        for (Map.Entry<LocalDateTime, ArrayList<Task>> entry : this.reminders.entrySet()) {
            tasks.append(entry.toString()).append("\n");
        }

        return tasks.toString();
    }
}
