public class AddToDoCommand extends Command {
    public AddToDoCommand() {}
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        try {
            String commandContent = command.substring(5);
            ToDo todo = new ToDo(commandContent);
            taskList.add(todo);
            storage.saveTasks(taskList);
            return ui.getTaskFinally(todo, taskList.size());
        } catch (StringIndexOutOfBoundsException e) {
            return ui.getTaskFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a todo cannot be empty.")
            );
        }
    }
}
