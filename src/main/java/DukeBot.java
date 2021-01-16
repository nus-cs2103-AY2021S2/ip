import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<Task> taskList;

    public DukeBot() {
        this.taskList = new ArrayList<>();
        handleCommand("welcome");
    }

    public void handleCommand(String command) {
        String commandOutput = "";
        commandOutput += "\t ";
        boolean isExit = false;

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
            commandOutput += "added: " + command;
            Task task = new Task(command);
            taskList.add(task);
            break;
        }

        commandOutput += "\n";

        respondToCommand(commandOutput, isExit);
    }

    private String getTaskListContents() {
        String contents = "\t Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            contents += String.format("\t %d.r%s\n", (i + 1), taskList.get(i).getTaskInfo());
        }

        return contents;
    }

    private void respondToCommand(String commandOutput, boolean isExit) {
        String responseMsg = "\n\t____________________________________________________________\n"
                + commandOutput
                + "\t____________________________________________________________\n\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
