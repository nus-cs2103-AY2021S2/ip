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
        String commandOutput = "";
        commandOutput += "\t ";

        switch (command) {
        case "welcome":
            commandOutput += "Hello! I'm Duke\n"
                    + "\t What can I do for you?";
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
            taskList.get(taskNum).markAsDone();
            commandOutput += "Nice! I've marked this task as done:\n"
                    + "\t " + getTaskInfo(taskNum);
            break;
        default:
            addTask(text);
            commandOutput += "added: " + text;
            break;
        }

        commandOutput += "\n";

        respondToCommand(commandOutput);
    }

    private String getTaskListContents() {
        String contents = "\t Here are the tasks in your list:\n";

        for (int i = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            contents += String.format("\t %d. [%s] %s\n", (i), task.getStatusIcon(), task.getDescription());
        }

        return contents;
    }

    private String getTaskInfo(int taskNum) {
        Task task;
        task = taskList.get(taskNum);
        return String.format("[%s] %s", task.getStatusIcon(), task.getDescription());
    }

    private void addTask(String task) {
        Task toDo = new Task(task);
        taskList.add(toDo);
    }

    private void respondToCommand(String commandOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + commandOutput
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
