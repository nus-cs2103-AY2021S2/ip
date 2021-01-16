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
        boolean exit = false;

        switch (command) {
            case "welcome":
                commandOutput = "\t Hello! I'm Duke\n"
                        + "\t What can I do for you?\n\n";
                break;
            case "list":
                commandOutput = "\t " + memoryContents() + "\n\n";
                break;
            case "blah":
                commandOutput = "\t blah\n\n";
                break;
            case "bye":
                commandOutput = "\t Bye. Hope to see you again soon!\n\n";
                exit = true;
                break;
            default:
                commandOutput = "\t added: " + command + "\n\n";
                memory.add(command);
                break;
        }

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
