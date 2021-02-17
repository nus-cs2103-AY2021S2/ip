package duke.command;


import duke.exception.DukeException;
import duke.task.TaskList;


public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand object
     *
     * @param userMessage The message that the user inputs for further execution.
     */
    public HelpCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * The execution after parsing, it prints out the command guide for the user.
     *
     * @param taskList The current taskList in the program. (This is to follow the execute method in Command Class, however in this method,
     *                  taskList is useless.
     *
     * @return The Duke robot massage to the GUI.
     */
    public String execute(TaskList taskList) throws DukeException {

        try {
            String start = "Here is the guide for you!";
            String addCommandHelp = "\n\n 1. To add a task: type in \n\n1.1 'todo {todoName}'\n\n 1.2 'event {eventName} /at {YYYY-MM-DD HH:MM}'\n\n "
                    + "1.3 'deadline {deadlineName} /by {YYYY-MM-DD HH:MM}'\n";

            String priorityCommandHelp = "\n\n 2. To add a priority for a task: \n\n2.1 You can add '-p {level of priority}' at the end of add task command.\n"
                    + "For example: 'todo go to school -p 4'\n\n 2.2 The priority level range from 1 to 5, and should be an integer.\n\n"
                    + "2.3 If you don't specify the level of priority, the default level is 1.\n";

            String listCommandHelp = "\n\n 3. To show the task: \n\n3.1 type in 'list' to show all the tasks in the list."
                    + "\n\n 3.2 type in 'list -p' to show the task sorted by priority\n";

            String doneCommandHelp = "\n\n 4. To mark the task as done: \n\n4.1 type in 'done {index of task}' to mark the task as done by the index you specified.\n";

            String deleteCommandHelp = "\n\n 5. To delete the task: \n\n5.1 type in 'delete {index of task}' to delete the task by the index you specified.\n";

            String searchCommandHelp = "\n\n 6. To search a task: \n\n6.1 type in 'find {task name fragment}' to search for the task whose name contains the fragment specified."
                    + "\n\n 6.2 type in 'search time {YYYY-MM-DD HH:MM}' search for the task whose time match the input.\n";

            String byeCommandHelp = "\n\n 7. To exit the program: \n\n7.1 type in 'bye'.\n";

            String botMessage = start + addCommandHelp + priorityCommandHelp + listCommandHelp
                    + doneCommandHelp + deleteCommandHelp + searchCommandHelp + byeCommandHelp;
            return botMessage;

        } catch (Exception e) {
            System.out.println("HelpCommand raises exception." +  e.getMessage());
            throw new DukeException("OOPS!!! There is something wrong with HelpCommand.");
        }
    }
}
