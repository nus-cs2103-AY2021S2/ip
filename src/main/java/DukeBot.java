import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<Task> taskList;
    private boolean isExit;
    private String commandOutput;

    public DukeBot() {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        commandOutput = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        respondToCommand(commandOutput);
    }

    public void handleCommand(String text) throws DukeException {
        commandOutput = "";
        String[] commandLine = text.split(" "); //entire line of command in String array
        String command = commandLine[0];
        String taskInfo = text.replaceFirst(command + " ", "");

        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            String errMsg;
            if (taskInfo.equals(command)) {
                errMsg = "☹ OOPS!!! The description of a " + command + " cannot be empty.";
                throw new DukeException(errMsg);
            }
        }

        if (command.equals("done") || command.equals("delete")) {
            String errMsg;
            if (taskInfo.equals(command)) {
                errMsg = "☹ OOPS!!! The selection for " + command + " cannot be empty.";
                throw new DukeException(errMsg);
            } else if (!isNumeric(taskInfo)) {
                errMsg = "☹ OOPS!!! The selection for " + command + " should be a valid Integer.";
                throw new DukeException(errMsg);
            }
        }

        switch (command) {
            case "list":
                listProcess();
                break;
            case "bye":
                byeProcess();
                break;
            case "done":
                doneProcess(taskInfo);
                break;
            case "delete":
                deleteProcess(taskInfo);
                break;
            case "event":
                eventProcess(taskInfo);
                break;
            case "deadline":
                deadlineProcess(taskInfo);
                break;
            case "todo":
                todoProcess(taskInfo);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        respondToCommand(commandOutput);
    }

    private void listProcess() {
        commandOutput = getTaskListContents();
    }

    private void byeProcess() {
        isExit = true;
        commandOutput = "Bye. Hope to see you again soon!";
    }

    private void doneProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        task.markAsDone();
        commandOutput = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    private void deleteProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        taskList.remove(task);
        commandOutput = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void eventProcess(String taskInfo) {
        String taskName = taskInfo.split(" /at")[0].replaceFirst("event ", "");
        String date = taskInfo.split(" /at ")[1];
        Task task = new Event(taskName, date);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void deadlineProcess(String taskInfo) {
        String taskName = taskInfo.split(" /by")[0].replaceFirst("deadline ", "");
        String date = taskInfo.split(" /by ")[1];
        Task task = new Deadline(taskName, date);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void todoProcess(String taskName) {
        Task task = new ToDo(taskName);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
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

    public void respondToCommand(String selectedOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + selectedOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

    private boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
