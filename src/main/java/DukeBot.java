import java.util.ArrayList;
import java.util.List;

public class DukeBot {
    private List<String> memory;

    public DukeBot() {
        this.memory = new ArrayList<>();
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
            commandOutput = getMemoryContents();
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
            memory.add(command);
            break;
        }
        commandOutput += "\n";

        respondToCommand(commandOutput, isExit);
    }

    private String getMemoryContents() {
        String contents = "";

        for (int i = 0; i < memory.size(); i++) {
            contents += String.format("\t %d. %s\n", (i + 1), memory.get(i));
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
