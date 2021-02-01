import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Duke's Parser. Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Takes in the user's command and decides the appropriate action to take, then returns the output string for
     * the UI object to display. Uses references to taskList, storage, scanner and ui in order to edit the task list,
     * store the task list when done, scan for arguments to commands and end the ui when needed.
     * @param nextCommand The next command for the parser to parse.
     * @param taskList The TaskList to be edited.
     * @param storage The Storage object that deals with the data file.
     * @param sc The provided Scanner to read command arguments.
     * @param ui The ui that called this parser. Referenced to end ui when needed.
     * @return Output String for the associated UI object to display.
     */
    public String parse(String nextCommand, TaskList taskList, Storage storage, Scanner sc, Ui ui) {
        StringBuilder outputString = new StringBuilder();

        switch (nextCommand) {
        case "list":
            outputString.append("    ____________________________________________________________\n");
            for (int taskNum = 0; taskNum < taskList.size(); taskNum++) {
                outputString.append("     " + (taskNum + 1) + "." + taskList.get(taskNum) + "\n");
            }
            outputString.append("    ____________________________________________________________\n");
            break;

        case "todo":
            String scanTest = sc.nextLine();
            if (scanTest.strip().equals("")) {
                outputString.append("    ____________________________________________________________\n" +
                        "     Error: ToDo entry format incorrect.\n" +
                        "    ____________________________________________________________\n");
                break;
            }
            ToDo nextToDo = new ToDo(scanTest.strip());
            taskList.add(nextToDo);
            outputString.append("    ____________________________________________________________\n" +
                    "     task.add:\n");
            outputString.append("       " + nextToDo + "\n" +
                    "     task.count = [" + taskList.size() + "].\n");
            outputString.append("    ____________________________________________________________\n");
            break;

        case "deadline":
            String[] deadLineArgs = sc.nextLine().split(" /by ");

            try {
                Deadline nextDeadLine = new Deadline(deadLineArgs[0].strip(), deadLineArgs[1]);
                taskList.add(nextDeadLine);
                outputString.append("    ____________________________________________________________\n" +
                        "     task.add:\n");
                outputString.append("       " + nextDeadLine + "\n" +
                        "     task.count = [" + taskList.size() + "].\n");
                outputString.append("    ____________________________________________________________\n");
            } catch (IndexOutOfBoundsException e) {
                outputString.append("    ____________________________________________________________\n");
                outputString.append("     Error: Deadline entry format incorrect.\n");
                outputString.append("    ____________________________________________________________\n");
            } catch (DateTimeParseException e) {
                outputString.append("    ____________________________________________________________\n");
                outputString.append("     Error: Deadline entry format incorrect.\n");
                outputString.append("    ____________________________________________________________\n");
            }
            break;

        case "event":
            String[] eventArgs = sc.nextLine().split(" /at ");

            try {
                Event nextEvent = new Event(eventArgs[0].strip(), eventArgs[1]);
                taskList.add(nextEvent);
                outputString.append("    ____________________________________________________________\n" +
                        "     task.add:\n");
                outputString.append("       " + nextEvent + "\n" +
                        "     task.count = [" + taskList.size() + "].\n");
                outputString.append("    ____________________________________________________________\n");
            } catch (IndexOutOfBoundsException e) {
                outputString.append("    ____________________________________________________________\n");
                outputString.append("     Error: Event entry format incorrect.\n");
                outputString.append("    ____________________________________________________________\n");
            }
            break;

        case "done":
            try {
                int doneTarget = sc.nextInt();
                outputString.append("    ____________________________________________________________\n" +
                        "     task.done = true:\n");
                Task targetTask = taskList.get(doneTarget - 1);
                targetTask.MarkAsDone();
                outputString.append("       " + targetTask + "\n");
                outputString.append("    ____________________________________________________________\n");

            } catch (Exception e) {
                outputString.append("    ____________________________________________________________\n");
                outputString.append("     Error: done command format incorrect.\n");
                outputString.append("    ____________________________________________________________\n");
            }
            break;

        case "delete":
            try {
                int removeTarget = sc.nextInt();
                outputString.append("    ____________________________________________________________\n" +
                        "     task.remove :\n");
                Task targetTask = taskList.get(removeTarget - 1);
                outputString.append("       " + targetTask + "\n");
                outputString.append("    ____________________________________________________________\n");

                taskList.remove(removeTarget - 1);
            } catch (Exception e) {
                outputString.append("    ____________________________________________________________\n");
                outputString.append("     Error: delete command format incorrect.\n");
                outputString.append("    ____________________________________________________________\n");
            }
            break;

        case "bye":
            try {
                storage.writeToStorage(taskList);
            } catch (IOException e) {
                outputString.append("IOException thrown, task list not saved.\n");
            }
            outputString.append("    ____________________________________________________________\n " +
                    "     goodBye.\n" +
                    "    ____________________________________________________________\n");
            ui.end();
            break;

        default:
            outputString.append("    ____________________________________________________________\n");
            outputString.append("     invalidCommand.\n");
            outputString.append("    ____________________________________________________________\n");
            break;
        }
        return outputString.toString();
    }
}
