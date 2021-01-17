import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<Task> taskList;
    private boolean isExit;

    public DukeBot() {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        respondToCommand("Hello! I'm Duke\n"
                + "\tWhat can I do for you?");
    }

    public void handleCommand(String text) throws DukeException {
        String[] commandLine = text.split(" "); //entire line of command in String array
        String command = commandLine[0];
        String commandOutput, taskName, date;
        Task task;

        if ((command.equals("todo") || command.equals("event") || command.equals("deadline"))
                && commandLine.length < 2) {
            String errMsg = "☹ OOPS!!! The description of a " + command + " cannot be empty.";
            throw new DukeException(errMsg);
        }

        switch (command) {
            case "list":
                commandOutput = getTaskListContents();
                break;
            case "bye":
                isExit = true;
                commandOutput = "Bye. Hope to see you again soon!";
                break;
            case "done":
                int taskNum = Integer.parseInt(text.split(" ")[1]);
                task = taskList.get(taskNum);
                task.markAsDone();
                commandOutput = "Nice! I've marked this task as done:\n"
                        + "\t" + task.toString();
                break;
            case "event":
                taskName = text.split(" /at")[0].replaceFirst("event ", "");
                date = text.split(" /at ")[1];
                task = new Event(taskName, date);
                taskList.add(task);
                commandOutput = "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            case "deadline":
                taskName = text.split(" /by")[0].replaceFirst("deadline ", "");
                date = text.split(" /by ")[1];
                task = new Deadline(taskName, date);
                taskList.add(task);
                commandOutput = "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            case "todo":
                taskName = text.split("todo ")[1];
                task = new ToDo(taskName);
                taskList.add(task);
                commandOutput = "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        respondToCommand(commandOutput);
    }

    private String getTaskListContents() {
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
    }

    private String getRemainingTasks() {
        return "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    public void respondToCommand(String commandOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + commandOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
