import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<Task> taskList;
    private boolean isExit;

    public DukeBot() {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        handleCommand("welcome");
    }

    public void handleCommand(String text) {
        String command = text.split(" ")[0];
        String taskName, date;
        String commandOutput = "";
        Task task;

        switch (command) {
            case "welcome":
                commandOutput += "Hello! I'm Duke\n"
                        + "\tWhat can I do for you?";
                break;
            case "list":
                commandOutput = getTaskListContents();
                break;
            case "blah":
                commandOutput += "blah";
                break;
            case "bye":
                isExit = true;
                commandOutput += "Bye. Hope to see you again soon!";
                break;
            case "done":
                int taskNum = Integer.parseInt(text.split(" ")[1]);
                task = taskList.get(taskNum);
                task.markAsDone();
                commandOutput += "Nice! I've marked this task as done:\n"
                        + "\t" + task.toString();
                break;
            case "event":
                taskName = text.split(" /at")[0].replaceFirst("event ", "");
                date = text.split(" /at ")[1];
                task = new Event(taskName, date);
                taskList.add(task);
                commandOutput += "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            case "deadline":
                taskName = text.split(" /by")[0].replaceFirst("deadline ", "");
                date = text.split(" /by ")[1];
                task = new Deadline(taskName, date);
                taskList.add(task);
                commandOutput += "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            case "todo":
                taskName = text.split("todo ")[1];
                task = new ToDo(taskName);
                taskList.add(task);
                commandOutput += "Got it. I've added this task: \n\t\t "
                        + task.toString() + getRemainingTasks();
                break;
            default:
                break;
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

    private void respondToCommand(String commandOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + commandOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
