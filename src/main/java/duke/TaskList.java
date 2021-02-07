package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * <code>TaskList</code> class contains the task list.
 * It has methods to carry out task actions - add, delete,
 * mark as done, commanded by the user.
 */
public class TaskList {
    protected List<Task> list;

    /**
     * Constructor for TaskList class when there is no existing
     * task list in the Duke application yet.
     * Initializes a new list to store tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class when there is an existing list of tasks in the
     * Duke application already.
     *
     * @param list List of existing tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a new task to the list of existing tasks.
     *
     * @param userInput User input of the new task to be added.
     * @return New task added by the user.
     * @throws InvalidDateException If the date is in an invalid format.
     */
    public Task addNewTask(String userInput)
            throws InvalidDateException {
        Task newTask;
        String taskName;

        try {
            if (userInput.startsWith("todo")) {
                taskName = userInput.substring(5);
                newTask = new ToDo(taskName);
            } else {
                int index = userInput.indexOf('/');

                String dateString = userInput.substring(index + 4);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

                if (userInput.startsWith("deadline")) {
                    newTask = new Deadline(userInput.substring(9, index), dateTime);
                } else {
                    assert userInput.startsWith("event");
                    newTask = new Event(userInput.substring(6, index), dateTime);
                }
            }
            list.add(newTask);
            return newTask;
        } catch (DateTimeException ex) {
            throw new InvalidDateException();
        }
    }

    /**
     * Marks an existing task in the list as done.
     *
     * @param userInput User input of the task to be marked as done.
     */
    public void markAsDone(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        list.get(taskNumber).markAsDone();
    }

    /**
     * Deletes an existing task in the list.
     *
     * @param userInput User input of the task to be deleted.
     */
    public void deleteTask(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        list.remove(taskNumber);
    }

    /**
     * Finds a task by searching for a keyword.
     *
     * @param userInput User input of the keyword to be found.
     * @return List of tasks that contains the keyword.
     */
    public List<Task> findTask(String userInput) {
        String keyword = userInput.split(" ")[1];
        List<Task> tempList = new ArrayList<>();

        for (Task task : list) {
            String taskDescription = task.getDescription();
            String[] taskDescriptionArr = taskDescription.split(" ");

            for (String currentWord : taskDescriptionArr) {
                if (currentWord.equals(keyword)) {
                    tempList.add(task);
                    break;
                }
            }
        }
        return tempList;
    }

    /**
     * Updates an existing task in the list.
     *
     * @param taskNumber Number of the task to be updated.
     * @param detailToUpdate Detail of the task to be updated.
     * @param newDetail New detail to update to.
     */
    public void updateTask(int taskNumber, String detailToUpdate, String newDetail)
            throws InvalidInputException {
        Task task = list.get(taskNumber);

        switch(detailToUpdate) {
        case "done":
            if (task.isDone()) {
                task.markAsUndone();
            } else {
                task.markAsDone();
            }
            break;

        case "description":
            task.setDescription(newDetail);
            break;

        case "date/time":
            task.setDateTime(newDetail);
            break;

        default:
            throw new InvalidInputException();
        }
    }
}
