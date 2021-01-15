import exceptions.DukeException;
import exceptions.DukeInsufficientParametersException;
import exceptions.DukeMissingFlagException;
import exceptions.DukeUnknownCommandException;
import tasks.DeadlineTask;
import tasks.EventsTask;
import tasks.Task;
import tasks.ToDoTask;

import java.util.ArrayList;

/**
 *  TaskManager manages all tasks given to Duke.
 *
 *  @author Yap Jing Kang
 */
public class TaskManager {
    protected ArrayList<Task> tasks;
    public static String[] acceptedCommands = {
            "todo", "deadline", "event"
    };

    /**
     *  Constructor initialises task list.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     *  Method to verify commands.
     *  Returns true if command is recognised.
     *
     *	@param command Command in question.
     *
     *  @return Command validity.
     */
    public boolean verifyCommand(String command) {
        for (String cmd : acceptedCommands) {
            if (command.equals(cmd)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  Method to add commands into task list.
     *
     *	@param command Command (with all parameters) in question.
     *
     */
    public void addTask(String[] command) {
        try {
            String taskType = command[0];
            Task newTask;

            if (!verifyCommand(taskType)) {
                throw new DukeUnknownCommandException();
            }

            if (command.length < 2) {
                throw new DukeInsufficientParametersException(taskType);
            } else if (taskType.equals("todo")) {
                newTask = new ToDoTask(command[1]);
            } else if (taskType.equals("deadline")) {
                String[] temp = command[1].split("/by ");
                if (temp.length < 2) {
                    throw new DukeMissingFlagException(taskType, "/by");
                }
                newTask = new DeadlineTask(temp[0], temp[1]);
            } else if (taskType.equals("event")) {
                String[] temp = command[1].split("/at ");
                if (temp.length < 2) {
                    throw new DukeMissingFlagException(taskType, "/at");
                }
                newTask = new EventsTask(temp[0], temp[1]);
            } else {
                throw new DukeUnknownCommandException();
            }
            tasks.add(newTask);
            System.out.println("    added: " + newTask.getName());
            System.out.printf("    Now you have %d task(s)%n",
                    tasks.size());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    /**
     *  Method to mark specified task done.
     *
     *	@param x tasks.Task index.
     *
     */
    public void markTaskAsDone(int x) {
        try {
            Task t = tasks.get(x - 1);
            if (t.markAsDone()) {
                System.out.println("    Marked as Done: ");
                System.out.println("      " + t.toString());
            } else {
                System.out.println("    tasks. Task is already done.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Index provided out of range");
        }
    }

    /**
     *  Method to remove specified.
     *
     *	@param x tasks.Task index.
     *
     */
    public void deleteTask(int x) {
        try {
            Task t = tasks.get(x - 1);
            tasks.remove(x - 1);
            System.out.println("    The following task has been removed: ");
            System.out.println("       " + t.toString());
            System.out.printf("    Now you have %d task(s)%n",
                    tasks.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Index provided out of range");
        }
    }

    /**
     *  Method to print all tasks
     */
    public void listTasks() {
        System.out.println("    Listing all tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println("     " +
                    num + ": " + tasks.get(i));
        }
    }
}
