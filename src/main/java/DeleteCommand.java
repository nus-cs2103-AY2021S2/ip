public class DeleteCommand extends Command {
    String type;
    public DeleteCommand(String t) {
        this.type = t;
    }

    public void execute(TaskList tasks, String input, DataHandler dataHandler) {
        if (Integer.parseInt(input.split(" ")[1]) > tasks.getSize()) {
            new InvalidInstructionException();
            return;
        } else {
            tasks.delete(input);
            dataHandler.saveData(tasks);
        }
    }

    public boolean isExit() {
        return false;
    }
}
