import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task>  taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public  Integer numberOfTask() {
        return taskList.size();
    }

    /**
     * Prints all tasks in taskList.
     */
    public void getTasks() {
        int i = 1;

        if (taskList.size() == 0) {
            System.out.println("____________________________________________________________\n"
                    + "You have no task for now, yay!\n"
                    + "____________________________________________________________");
        } else {

            System.out.println("____________________________________________________________\n"
                    + (numberOfTask() == 1 ? "Here is the task in your list: \n" : "Here are the tasks in your list: \n"));

            for (Task t : taskList) {
                System.out.println(i + ". "
                        + t.toString());
                i++;
            }

            System.out.println("____________________________________________________________\n");
        }
    }

    /**
     * Converts isDone field of the task at given index to true.
     *
     * @param index of the task in taskList
     */
    public void updateTaskStatus(Integer index) {
        taskList.get(index - 1).markAsDone();

        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + taskList.get(index - 1).toString()
                + "\n____________________________________________________________\n");
    }

    /**
     * Adds a todo object to taskList.
     * If todo description is in the wrong format, error is thrown.
     *
     * @param todo description of todo task
     * @throws InvalidTodoException if description of todo is empty
     */
    public void addTodos(String todo) throws InvalidTodoException {
        if (todo.length() <= 5) {
            throw new InvalidTodoException();
        } else {
            String taskContent = todo.substring(4);
            Todo myTask = new Todo(taskContent);
            taskList.add(myTask);
            System.out.println("____________________________________________________________\n"
                    + "Got it. I've added this task: \n"
                    + myTask.toString());
            Remark();
        }
    }

    /**
     * Prints the number of tasks in the list.
     */
    public void Remark() {
        if (numberOfTask() <= 1) {
            System.out.println("Now you have " + numberOfTask() + " task in the list."
                    + "\n____________________________________________________________\n");
        } else {
            System.out.println("Now you have " + numberOfTask() + " tasks in the list."
                    + "\n____________________________________________________________\n");
        }
    }

    /**
     * Adds a deadline object to taskList.
     * If deadline description is in the wrong format, error is thrown.
     *
     * @param deadline description of a deadline task.
     * @throws InvalidDeadlineException if deadline description is empty or description format is wrong.
     */
    public void addDeadlines(String deadline) throws InvalidDeadlineException {
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
                        taskList.add(myTask);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task: \n"
                                + myTask.toString());
                        Remark();
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
    public void addEvents(String event) throws InvalidEventException {
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
                        taskList.add(myTask);

                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task: \n"
                                + myTask.toString());
                        Remark();

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
    public void Delete(Integer index) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + taskList.get(index - 1).toString()
                + "\n____________________________________________________________\n");

        taskList.remove(index - 1);

    }


}