import exceptions.DukeException;
import exceptions.InsufficientParametersException;
import exceptions.MissingFlagException;
import exceptions.UnknownCommandException;

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
                throw new UnknownCommandException();
            }

            if (command.length < 2) {
                throw new InsufficientParametersException(taskType);
            } else if (taskType.equals("todo")) {
                newTask = new ToDoTask(command[1]);
            } else if (taskType.equals("deadline")) {
                String[] temp = command[1].split("/by ");
                if (temp.length < 2) {
                    throw new MissingFlagException(taskType, "/by");
                }
                newTask = new DeadlineTask(temp[0], temp[1]);
            } else if (taskType.equals("event")) {
                String[] temp = command[1].split("/at ");
                if (temp.length < 2) {
                    throw new MissingFlagException(taskType, "/at");
                }
                newTask = new EventsTask(temp[0], temp[1]);
            } else {
                throw new UnknownCommandException();
            }
            tasks.add(newTask);
            System.out.println("    added: " + newTask.getName());
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    /**
     *  Method to mark specified task done.
     *
     *	@param x Task index.
     *
     */
    public void markTaskAsDone(int x) {
        Task t = tasks.get(x - 1);
        if (t.markAsDone()) {
            System.out.println("    Marked as Done: ");
            System.out.println("      " + t.toString());
        } else {
            System.out.println("    Task is already done.");
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
