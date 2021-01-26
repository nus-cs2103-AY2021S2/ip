public class ListCommand extends Command{
    private String date;

    public ListCommand() {
        this.date = null;
    }

    public ListCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int listSize = taskList.size();
        if (listSize <= 0) {
            throw new DukeException("Your task list is empty.");
        }

        //Clone the task list for filtering
        TaskList printTaskList = taskList.clone();
        //If there is date in the command, only display the events or deadlines on the particular date.
        if (date != null) {
            if(!date.isEmpty() || !date.isBlank())
                printTaskList = printTaskList.filterByDate(date);
        }

        if (printTaskList.size() <= 0) {
            throw new DukeException(String.format("You have no task on %s.", DateHelper.formatDate(date)));
        }

        ui.printList(printTaskList);
    }
}
