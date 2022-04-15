import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.ToDoubleFunction;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected Ui ui;

    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public int numberOfTask() {
        return taskList.size();
    }

    /**
     * Prints all tasks in taskList.
     */
    public String getTasks() {
        if (taskList.size() == 0) {
            return ui.noTask();
        } else {
            return ui.listingTasksTitle(numberOfTask())
                    + ui.printTasks(taskList);
        }
    }

    /**
     * Converts isDone field of the task at given index to true.
     *
     * @param index of the task in taskList
     */
    public String updateTaskStatus(Integer index) {
        taskList.get(index - 1).markAsDone();
        return ui.doneConfirmMessage(taskList.get(index - 1).toString());
    }

    /**
     * Adds a todo object to taskList.
     * If todo description is in the wrong format, error is thrown.
     *
     * @param todo description of todo task
     * @throws InvalidTodoException if description of todo is empty
     */
    public String addTodos(String todo) throws InvalidTodoException {
        if (todo.length() <= 5) {
            throw new InvalidTodoException();
        } else {
            String taskContent = todo.substring(4);
            if (!taskContent.matches(".*\\w.*")) {
                throw new InvalidTodoException();
            } else {
                assert taskContent.length() > 0 : "todo description is empty";
                Todo myTask = new Todo(taskContent);
                assert myTask.isDone == false : "newly added todo status should be \u2718";
                taskList.add(myTask);
                return ui.addTaskConfirmMessage(myTask.toString()) + remark();
            }
        }
    }

    /**
     * Prints the number of tasks in the list.
     */
    public String remark() {
        return ui.taskNumberReminder(numberOfTask());
    }

    /**
     * Adds a deadline object to taskList.
     * If deadline description is in the wrong format, error is thrown.
     *
     * @param deadline description of a deadline task.
     * @throws InvalidDeadlineException if deadline description is empty or description format is wrong.
     */
    public String addDeadlines(String deadline) throws InvalidDeadlineException {
        if (deadline.length() <= 9) {
            throw new InvalidDeadlineException(deadline);
        } else {
            String[] taskSegments = deadline.split(" /by ");
            if (taskSegments.length < 2) {
                throw new InvalidDeadlineException(deadline);
            } else {
                String taskContent = taskSegments[0].substring(8);
                if (taskContent.equals(" ") || taskContent.equals("")) {
                    throw new InvalidDeadlineException(deadline);
                } else {
                    String taskTime = taskSegments[taskSegments.length - 1];
                    try {
                        LocalDate t = LocalDate.parse(taskTime);
                        Deadline myTask = new Deadline(taskContent, t);
                        assert myTask.isDone == false : "newly added deadline status should be \u2718";
                        taskList.add(myTask);
                        return ui.addTaskConfirmMessage(myTask.toString()) + remark();
                    } catch (DateTimeParseException e) {
                        throw new InvalidDeadlineException(deadline);
                    }

                }
            }
        }
    }

    /**
     * Adds an event object to taskList.
     * If event description is in the wrong format, error is thrown.
     *
     * @param event description of an event task.
     * @throws InvalidEventException if description of the event is empty or format of description is wrong.
     */
    public String addEvents(String event) throws InvalidEventException {
        if (event.length() <= 6) {
            throw new InvalidEventException(event);
        } else {
            String[] taskSegments = event.split(" /at ");
            if (taskSegments.length < 2) {
                throw new InvalidEventException(event);
            } else {
                String taskContent = taskSegments[0].substring(5);
                if (taskContent.equals(" ") || taskContent.equals("")) {
                    throw new InvalidEventException(event);
                } else {
                    try {
                        String taskTime = taskSegments[taskSegments.length - 1];
                        LocalDateTime t = LocalDateTime.parse(taskTime);
                        Event myTask = new Event(taskContent, t);
                        assert myTask.isDone == false : "newly added deadline status should be \u2718";
                        taskList.add(myTask);
                        return ui.addTaskConfirmMessage(myTask.toString()) + remark();

                    } catch (DateTimeParseException e) {
                        throw new InvalidEventException(event);
                    }
                }
            }
        }
    }

    /**
     * Deletes a task from tasks list at given index.
     *
     * @param index of task in taskList to be deleted.
     */
    public String delete(Integer index) {
        int beforeSize = taskList.size();
        String deletedTask = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        int afterSize = taskList.size();
        assert (beforeSize - afterSize) == 1 : "task is not deleted";
        return ui.deleteTaskConfirmMessage(deletedTask);
    }

    /**
     * Finds tasks by searching for a keyword.
     * Prints the list of tasks with description containing the keyword.
     *
     * @param keyword within a task description
     */

    public String findTask(String keyword) {
        ArrayList<Task> searchResults = new ArrayList<>();
        if (taskList.size() == 0) {
            return ui.noTask();
        } else {
            taskList.forEach((t) -> {
                if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(t);
                }
            });
        }

        if (searchResults.size() == 0) {
            return ui.noMatchingTaskMessage();
        } else {
            return ui.matchingTasksTitle(searchResults.size()) + ui.printTasks(searchResults);
        }
    }

}