public class Command {
    public String action;
    public String description;
    public boolean isExit = false;

    public Command(String line) {
        action = new SplitString(line, " ").getFirstString().toLowerCase();
        description = new SplitString(line, " ").getSecondString();
        this.isExit = action.equals("bye");
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        String messageToPrint = "";
        switch (action) {
        case "list":
            messageToPrint = taskList.listTask();
            break;
        case "todo": //fallthrough
        case "deadline": //fallthrough
        case "event":
            messageToPrint = taskList.addTask(action, description);
            break;
        case "done":
            messageToPrint = taskList.doTask(description);
            break;
        case "delete":
            messageToPrint = taskList.deleteTask(description);
            break;
        case "bye":
            return;
        default:
            messageToPrint = "Oops, the command '" + action + "' is not recognised.";
        }
        ui.printMessage(messageToPrint);
        storage.save(taskList);
    }

    public boolean isExit() {
        return isExit;
    }

}
