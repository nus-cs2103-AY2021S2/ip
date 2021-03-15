public class DeleteCommand extends Command {
    private String secondPartOfCommand;

    public DeleteCommand(String secondPartOfCommand) {
        this.secondPartOfCommand = secondPartOfCommand;
    }

    public String action() {
        try {
            int num = Parser.makeToInt(secondPartOfCommand);
            Task del = TaskList.deleteTask(num - 1);
            super.writeToFile();
            return Ui.deleteTask(del, TaskList.getListSize());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
