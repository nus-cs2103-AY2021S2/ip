public class AddCommand extends Command {
    String type;
    public AddCommand(String t) {
        this.type = t;
    }

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
        String type = input.split(" ")[0];

        if (type.equals("todo")) {
            tasks.addToDo(input);
            dataHandler.saveData(tasks);
        } else if (type.equals("deadline")) {
            tasks.addDeadline(input);
            dataHandler.saveData(tasks);
        } else if (type.equals("event")) {
            tasks.addEvent(input);
            dataHandler.saveData(tasks);
        } else {
            new InvalidInstructionException();
            return;
        }
    }

    public boolean isExit() {
        return false;
    }
}
