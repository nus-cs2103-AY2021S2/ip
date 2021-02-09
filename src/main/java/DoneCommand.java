public class DoneCommand extends Command {
    private String secondPartOfCommand;

    public DoneCommand(String secondPartOfCommand) {
        this.secondPartOfCommand = secondPartOfCommand;
    }

    public String action() {
        try {
            int num = Parser.makeToInt(secondPartOfCommand);
            Task done = TaskList.doneTask(num - 1);
            super.writeToFile();
            return Ui.doneTask(done);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}
