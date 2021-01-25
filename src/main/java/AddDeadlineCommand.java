public class AddDeadlineCommand extends Command{
    public AddDeadlineCommand(String userMessage){
        super(userMessage);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException{
        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");

        int spaceIndex = userMessage.indexOf(" ");
        int dateIndex = userMessage.indexOf('/');
        if (dateIndex == -1) throw new DukeException("OOPS!!! I can't find your deadline time.");
        if (spaceIndex == -1 || dateIndex - spaceIndex == 1) throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");

        String deadlineName = userMessage.substring(spaceIndex + 1,dateIndex - 1);
        String by = userMessage.substring(dateIndex + 4);
        Deadline deadline = new Deadline(deadlineName,by);
        taskList.addTasks(deadline);

        builder.append("[" + deadline.getStatusIcon() + "] " + deadline.getTaskName());
        builder.append("\nNow you have " + taskList.getNumOfTasks() + " tasks in the list.");
        String botMessage = builder.toString();
        ui.display(botMessage);
    }
}
