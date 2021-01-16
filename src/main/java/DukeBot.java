public class DukeBot {

    public DukeBot() {
        handleCommand("welcome");
    }

    public void handleCommand(String command) {
        String commandOutput = "";
        boolean exit = false;

        switch(command) {
            case "welcome":
                commandOutput = "\t Hello! I'm Duke\n"
                    + "\t What can I do for you?\n\n";
                break;
            case "list":
                commandOutput = "\t list\n\n";
                break;
            case "blah":
                commandOutput = "\t blah\n\n";
                break;
            case "bye":
                commandOutput = "\t Bye. Hope to see you again soon!\n\n";
                exit = true;
                break;
        }

        respondToCommand(commandOutput, exit);
    }

    private void respondToCommand(String commandOutput, boolean exit) {
        String responseMsg = "\n\t____________________________________________________________\n"
                + commandOutput
                + "\t____________________________________________________________\n\n";

        System.out.println(responseMsg);

        if(exit) {
            System.exit(0);
        }
    }

}
