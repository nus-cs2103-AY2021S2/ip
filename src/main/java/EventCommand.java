public class EventCommand extends Command {

    private String command;

    public EventCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeMissingInputException, DukeWrongInputException {
        String description = "";
        String time = "";
        boolean foundAt = false;
        String [] commandArr = command.trim().split(" ");

        if (command.equals("event")) {
            throw new DukeMissingInputException("OOPS!!! The description of an event cannot be empty.");
        } else {
            for (int i = 1; i < commandArr.length; i++) {
                if (commandArr[i].equals("/at")){
                    foundAt = true;
                    continue;
                }
                if (foundAt) {
                    time += (commandArr[i] + " ");
                } else {
                    description += (commandArr[i] + " ");
                }
            }
        }
        time = time.trim();
        Event newEvent = new Event(description, time);
        taskList.add(newEvent);
        ui.showTaskAdded(newEvent);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}