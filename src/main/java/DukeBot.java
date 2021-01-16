import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<Task> taskList;
    private boolean isExit = false;

    public DukeBot() {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        handleCommand("welcome");
    }

    public void handleCommand(String command) {
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
            commandOutput += "Bye. Hope to see you again soon!";
            isExit = true;
            break;
        default:
            if (command.length() > 1 && command.split(" ")[0].equals("done")) {
                int taskNum = Integer.parseInt(command.split(" ")[1] + 1);
                taskList.get(taskNum).markAsDone();
                commandOutput += "Nice! I've marked this task as done:\n"
                    + "\t " + getTaskInfo(taskNum);
            } else { //Add a task
                addTask(command);
                commandOutput += "added: " + command;
            }
            break;
        }

        commandOutput += "\n";

        respondToCommand(commandOutput);
    }

    private String getTaskListContents() {
        String contents = "\t Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            contents += String.format("\t %d. [%s] %s\n", (i + 1), task.getStatusIcon(), task.getDescription());
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
