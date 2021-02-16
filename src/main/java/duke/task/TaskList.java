/**
 * This TaskList class handles the logic of adding and deleting tasks of Duke
 *
 * @param task Task arraylist to hold the current input tasks
 *
 * @author WangYihe
 * @author E0424695
 */

package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

//to handle date and time
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    public final List<Task> task;

    public TaskList() {
        task = new ArrayList<>();
    }

    /**
     * return task list size, ie, no. of tasks in the list
     */
    public int getSize() {
        assert task.size() >= 0;
        return task.size();
    }

    /**
     * add task to the list
     */
    public void add(Task t) {
        task.add(t);
    }

    /**
     * get the number i task
     */
    public Task get(int i) {
        assert i >= 0 : "Getter error, pointer less than 0";
        return task.get(i);
    }

    /**
     * check whether is date format
     */
    public boolean isDateFormat(String date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date.length() != 10) {
            return false;
        }
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            //TODO: handle exception
            return false;
        }
        return true;
    }

    /**
     * parser for add tasks
     */
    public String add(String[] userInput, Ui ui) throws DukeException {
        String reportString = " ";
        switch (userInput[0]) {
        case "todo":
            duke.task.Todo t = new Todo(userInput[1]);
            task.add(t);
            reportString = ui.reportTask(t, this);
            break;

        case "deadline":
            String[] deadlineArr = userInput[1].split(" /by ", 2);
            if (deadlineArr.length != 2) {
                throw new DukeException("Missing component: due date");
            }
            String time = deadlineArr[1];
            if (isDateFormat(time, "yyyy-mm-dd") || isDateFormat(time, "yyyy-m-dd") || isDateFormat(time, "yyyy-mm-d") || isDateFormat(time, "yyyy-m-d")) {
                LocalDate date = LocalDate.parse(time);
                time = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            }
            duke.task.Deadline d = new Deadline(deadlineArr[0], time);
            task.add(d);
            reportString = ui.reportTask(d, this);
            break;

        case "event":
            String eventArr[] = userInput[1].split(" /at ", 2);
            if (eventArr.length != 2) {
                throw new DukeException("Missing component: event date and time");
            }
            duke.task.Event e = new Event(eventArr);
            task.add(e);
            reportString = ui.reportTask(e, this);
            break;
        default:
            break;
        }
        return reportString;
    }

    /**
     * mark task as done
     */
    public String doneTask(String inputIndex, Ui ui) {
        try {
            ui.printLine();
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            if (t.isDone()) {
                return "This task was marked as done before";
            } else {
                t.markAsDone();
                return "You have done the following task: \n" +
                        t.toString() + "\n";
            }
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            return "Sorry, I cannot find this task, please check your list again";
        } catch (NumberFormatException e) {
            return "Sorry, number not recognized";
        }
    }

    /**
     * delete task from list
     */
    public String deleteTask(String inputIndex, Ui ui) {
        try {
            ui.printLine();
            int index = Integer.parseInt(inputIndex.trim());
            Task t = task.get(index - 1);
            task.remove(t);
            return "The following task has been deleted:\n" +
                    t.toString() + "\n" +
                    "You now have " + task.size() + "  task in your list";
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            return "Sorry, I cannot find this task, please check your list again";
        } catch (NumberFormatException e) {
            return "Sorry, number not recognized";
        }
    }

    /**
     * print out task
     */
    public String printTask(Ui ui) {
        String taskList;
        taskList = "Here is your current tasks\n";
        for (int i = 1; i <= task.size(); ++i) {
            taskList += i + "." + task.get(i - 1).toString() + "\n";
        }
        return taskList;
    }

    /**
     * check whether a task is in the list
     */
    public List<Task> findTask(String taskInfo) {
        List<Task> findedTasks = new ArrayList<>();
        for (Task t : task) {
            if (t.getDescription().contains(taskInfo)) {
                findedTasks.add(t);
            }
        }
        return findedTasks;
    }
}
