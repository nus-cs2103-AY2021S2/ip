package duchess;

import java.time.LocalDate;
import java.util.ArrayList;

import duchess.Tasks.Deadline;
import duchess.Tasks.Event;
import duchess.Tasks.Task;
import duchess.Tasks.Todo;


public class TaskList {
    /**
     * Array list of tasks
     */
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns tasklist
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Retrieves task from TaskList and marks
     * it as completed
     *
     * @param index position of task in TaskList
     */
    public void markComplete(int index) {
        Task temp = this.taskList.get(index - 1);
        assert temp != null : "Task index out of bounds";
        temp.checkTask();
    }

    /**
     * Removes task from TaskList
     *
     * @param task index of task in TaskList
     */
    public void deleteTask(int task) {
        this.taskList.remove(task - 1);
    }

    /**
     * Adds new task to TaskList
     *
     * @param task to be added
     */
    public void storeTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Retrieves and returns task from TaskList
     *
     * @param index of task in TaskList
     * @return Chosen Task
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Find tasks that matches the given string
     *
     * @param s string to search for
     * @return message with matching tasks
     */
    public String findTasks(String s) {
        ArrayList<Task> matched = new ArrayList<>();
        String msg = "";
        for (Task t : taskList) {
            String name = t.getName();
            if (name.contains(s)) {
                matched.add(t);
            }
        }
        if (matched.size() >= 1) {
            msg += "Duchess: Here are the matching tasks in your list:";
            for (int i = 0; i < matched.size(); i++) {
                msg += "\n" + (i + 1) + ". " + matched.get(i);
            }
        } else {
            msg += "Duchess: No matching tasks found!";
        }
        return msg;
    }

    /**
     * Adds new todo task to taskList and returns todo task
     *
     * @param name  name of new todo task
     * @param tasks tasklist of tasks
     * @return new todo task
     */
    public Task addTodo(String name, TaskList tasks) {
        Task todo = new Todo(name);
        tasks.storeTask(todo);
        return todo;
    }

    /**
     * Adds new event to taskList and returns event
     *
     * @param name  name of new event
     * @param date  date of event as string
     * @param tasks taskList of tasks
     * @return new event
     */
    public Task addEvent(String name, String date, TaskList tasks) {
        Task event = new Event(name, date);
        tasks.storeTask(event);
        return event;
    }

    /**
     * Adds new deadline to taskList and returns deadline
     *
     * @param name    name of new Deadline
     * @param dueDate due date of deadline task
     * @param tasks   taskList of tasks
     * @return new deadline
     */
    public Task addDeadline(String name, String dueDate, TaskList tasks) {
        Task deadline = new Deadline(name, dueDate);
        tasks.storeTask(deadline);
        return deadline;
    }

    /**
     * Checks off task of specified index and returns task
     *
     * @param index index of task to be checked off
     * @param tasks taskList of tasks
     * @return task that has been checked off
     */
    public Task completeTask(int index, TaskList tasks) {
        Task t = tasks.getTask(index);
        tasks.markComplete(index);
        return t;
    }

    /** Finds tasks that have to completed by today
     *
     * @param tasks taskList of tasks
     * @return String of tasks if any
     */
    public String getReminder(TaskList tasks) {
        String msg = "Duchess: These are your tasks for today: ";
        LocalDate today = LocalDate.now();
        for (Task t : tasks.getTaskList()) {
            String date;
            if (t instanceof Event) {
                date = ((Event) t).getTime();
                Boolean completed = t.getCompleted();
                if (!completed && date.compareTo(today.toString()) == 0) {
                    msg += "\n" + t;
                }
            } else if (t instanceof Deadline) {
                date = ((Deadline) t).getDeadline();
                Boolean completed = t.getCompleted();
                if (!completed && date.compareTo(today.toString()) == 0) {
                    msg += "\n" + t;
                }
            }
        }
        if (msg.length() > 50) {
            return msg;
        } else {
            return "Duchess: Woohoo, you don't have tasks for today!";
        }
    }

    /** Finds tasks that have to be completed in the week
     *
     * @param tasks taskList of tasks
     * @return String of tasks if any
     */
    public String getNextReminder(TaskList tasks) {
        String msg = "Duchess: These are your tasks for this week: ";
        LocalDate today = LocalDate.now();
        LocalDate weekToday = today.plusWeeks(1);
        for (Task t : tasks.getTaskList()) {
            String date;
            if (t instanceof Event) {
                date = ((Event) t).getTime();
                Boolean completed = t.getCompleted();
                Boolean withinWeek = today.toString().compareTo(date) <= 0 && date.compareTo(weekToday.toString()) < 0;
                if (!completed && withinWeek) {
                    msg += "\n" + t;
                }
            } else if (t instanceof Deadline) {
                date = ((Deadline) t).getDeadline();
                Boolean completed = t.getCompleted();
                Boolean withinWeek = today.toString().compareTo(date) <= 0 && date.compareTo(weekToday.toString()) < 0;
                if (!completed && withinWeek) {
                    msg += "\n" + t;
                }
            }
        }
        if (msg.length() > 50) {
            return msg;
        } else {
            return "Duchess: Woohoo, you don't have tasks for next week!";
        }
    }
}
