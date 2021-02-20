package duke;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The class that contains the task list. It has operations to add/delete
 * tasks in the list.
 */
class TaskList {
    private ArrayList<Task> tasks;
    private boolean canWriteToHardDisk = true;

    /**
     * If there is no existing data on the local hard disk, Dukebot will startup with an
     * empty task list. If there is an existing text file, then Dukebot will copy over the
     * text file into the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        try {
            File storageTextFile = Storage.initialiseFile();
            Storage.convert(storageTextFile, this.tasks);
        } catch (IOException | ArrayIndexOutOfBoundsException exception) {
            this.canWriteToHardDisk = false;
        }
    }

    /**
     * Iterates over the task list and prints out all the tasks.
     *
     * @return A string representation of all the tasks in the list.
     */
    public String listTask() {
        String stringRepresentation = "";
        stringRepresentation += "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            stringRepresentation += "\n";
            Task currentTask = tasks.get(i);
            stringRepresentation += (i + 1) + "." + currentTask;
        }
        return stringRepresentation;
    }

    /**
     * Set the task at a particular index as done and also updates the local text file
     * if possible.
     *
     * @param index The index of the completed task.
     * @return A string representation of the corresponding message when a task
     * is completed.
     */
    protected String doneTask(int index) {
        String dukeMessage = "";
        if (this.tasks.size() < index) {
            dukeMessage += "Invalid input for done command, invalid index";
            return dukeMessage;
        }
        Task completedTask = this.tasks.get(index - 1);
        if (completedTask.isComplete()) {
            dukeMessage += "This task is already completed.";
            return dukeMessage;
        }
        completedTask.completeTask();
        dukeMessage += "Nice! I've marked this task as done:\n " + completedTask;
        writeToHardDisk();
        return dukeMessage;
    }

    /**
     * Deletes the task at a particular index and also updates the local text
     * file if possible.
     *
     * @param index The index of the task to be deleted.
     * @return A string representation of the corresponding message when a
     * task is deleted.
     */
    public String delete(int index) {
        String dukeMessage = "";
        int indexToDelete = index;
        if (this.tasks.size() < indexToDelete) {
            dukeMessage += "Invalid index";
            return dukeMessage;
        }
        dukeMessage += "Noted. I've removed this task:\n" + " ";
        dukeMessage += this.tasks.remove(indexToDelete - 1) + "\n" + this;
        writeToHardDisk();
        return dukeMessage;
    }

    /**
     * Adds a new ToDo task to the task list and also updates the local text
     * file if possible.
     *
     * @param taskName The name of the ToDo task.
     * @return A string representation of the corresponding message when a
     * Todo task is created.
     */
    public String addToDo(String taskName) {
        String dukeMessage = "";
        ToDo newTask = new ToDo(taskName);
        this.tasks.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n" + this;
        writeToHardDisk();
        return dukeMessage;
    }
    /**
     * Adds a new event task to the task list and also updates the local text
     * file if possible.
     *
     * @param taskName The name of the event task.
     * @param date The date on which the event takes place.
     * @return A string representation of the corresponding message when an
     * event task is created.
     */
    public String addEvent(String taskName, LocalDate date) {
        String dukeMessage = "";
        Event newTask = new Event(taskName, date);
        this.tasks.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n" + this;
        writeToHardDisk();
        return dukeMessage;
    }
    /**
     * Adds a new deadline task to the task list and also updates the local
     * text file if possible.
     *
     * @param taskName The name of the deadline task.
     * @param date The date on which the deadline is due.
     * @return A string representation of the corresponding message when a
     * deadline task is created.
     */
    public String addDeadline(String taskName, LocalDate date) {
        String dukeMessage = "";
        Deadline newTask = new Deadline(taskName, date);
        this.tasks.add(newTask);
        dukeMessage += "Got it. I've added this task:\n";
        dukeMessage += "  " + newTask + "\n" + this;
        writeToHardDisk();
        return dukeMessage;
    }

    /**
     * Returns a string representing a list of tasks that contains the specified
     * input keyword.
     *
     * @param keyword The keyword to filter the tasks.
     * @return A string representation of the corresponding list of tasks that
     * contain the specified keyword.
     */
    public String find(String keyword) {
        String dukeMessage = "";
        boolean isFirst = true;
        for (Task currentTask : tasks) {
            if (currentTask.getTaskName().contains(keyword)) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    dukeMessage += "\n";
                }
                dukeMessage += currentTask;
            }
        }
        if (dukeMessage.length() == 0) {
            dukeMessage += "No task with that keyword has been found";
        }
        return dukeMessage;
    }

    /**
     * Sorts the list of tasks according to the criteria specified by the input
     * @param sortCriteria The criteria to sort the tasks by.
     * @return A string representation of the list of tasks after being sorted.
     */
    public String sort(String sortCriteria) {
        if (sortCriteria.equals("name")) {
            return sortByName();
        } else if (sortCriteria.equals("donefirst")) {
            return sortByDoneFirst();
        } else if (sortCriteria.equals("notdonefirst")) {
            return sortByNotDoneFirst();
        } else {
            return "Invalid sorting criteria.";
        }
    }

    /**
     * Displays the number of tasks in the list.
     *
     * @return A string representation that shows the number of tasks in the
     * list.
     */
    @Override
    public String toString() {
        String taskSingularOrPlural = "";
        if (this.tasks.size() == 1) {
            taskSingularOrPlural = "task";
        } else {
            taskSingularOrPlural = "tasks";
        }
        String message = "Now you have " + this.tasks.size() + " ";
        message += taskSingularOrPlural + " in the list";
        return message;
    }

    private String sortByName() {
        tasks.sort(new NameComparator());
        writeToHardDisk();
        String dukeMessage = "The list is now sorted by name\n";
        dukeMessage += this.listTask();
        return dukeMessage;
    }

    private String sortByDoneFirst() {
        tasks.sort(new DoneFirstComparator());
        writeToHardDisk();
        String dukeMessage = "The list is now sorted with the completed tasks "
                + "being listed first\n";
        dukeMessage += this.listTask();
        return dukeMessage;
    }

    private String sortByNotDoneFirst() {
        tasks.sort(new NotDoneFirstComparator());
        writeToHardDisk();
        String dukeMessage = "The list is now sorted with the incomplete tasks"
                + " being listed first\n";
        dukeMessage += this.listTask();
        return dukeMessage;
    }
    private void writeToHardDisk () {
        if (canWriteToHardDisk) {
            try {
                Storage.updateTextFile(this.tasks);
            } catch (IOException exception) {
                canWriteToHardDisk = false;
            }
        }
    }
}
