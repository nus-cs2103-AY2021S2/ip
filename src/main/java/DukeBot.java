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
        boolean exit = false;

        switch (command) {
            case "welcome":
                commandOutput += "Hello! I'm Duke\n"
                        + "\t What can I do for you?";
                break;
            case "list":
                commandOutput = memoryContents();
                break;
            case "blah":
                commandOutput += "blah";
                break;
            case "bye":
                commandOutput += "Bye. Hope to see you again soon!";
                exit = true;
                break;
            default:
                commandOutput += "added: " + command;
                memory.add(command);
                break;
        }
        commandOutput += "\n";

        respondToCommand(commandOutput, exit);
    }

    private String memoryContents() {
        String contents = "";

        for(int i = 0; i < memory.size(); i++) {
            contents += String.format("\t %d. %s\n", (i + 1), memory.get(i));
        }

        return contents;
    }

    private void respondToCommand(String commandOutput, boolean exit) {
        String responseMsg = "\n\t____________________________________________________________\n"
                + commandOutput
                + "\t____________________________________________________________\n\n";

        System.out.println(responseMsg);

        if (exit) {
            System.exit(0);
        }
    }

}
