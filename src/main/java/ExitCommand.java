public class ExitCommand extends Command {
    String type;
    public ExitCommand(String t) {
        this.type = t;

    }
    String line = "------------------------------------------";

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
        if (input.equals("bye")) {
            System.out.println(line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
            return;
        }
    }

    public boolean isExit() {
        return true;
    }
}
