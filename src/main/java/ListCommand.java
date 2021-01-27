public class ListCommand extends Command{

    private String command;

    public ListCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        ui.showListItems();
        for (int i = 1; i <= taskList.getTaskListLength(); i++) {
            String output = String.format("%s. %s", i, taskList.getTaskAtIndex(i - 1).toString());
            System.out.println(output);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}