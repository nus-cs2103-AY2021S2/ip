public class FindCommand implements Command {
    private String secondPartOfCommand;

    public FindCommand(String secondPartOfCommand) {
        this.secondPartOfCommand = secondPartOfCommand;
    }

    public String action() {
        try {
            return TaskList.findTask(secondPartOfCommand);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

}
