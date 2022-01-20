import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        // Constant Declarations
        final Scanner sc = new Scanner(System.in);

        // Variable Declarations
        TaskList taskList = new TaskList();
        String[] formatInputTxt;
        String inputTxt, command, description, due;
        int taskId;
        boolean status = true;

        // Print Welcome Message
        new ResponePrinter(Templates.greetingMsg).print();

        while (status) {
            inputTxt = sc.nextLine();
            try {
                if (inputTxt.equals("") || inputTxt.equals("\n"))
                    throw new DukeException(Templates.emptyInputMsg);
                command = inputTxt.split(" ")[0].toLowerCase();
                switch (command) {
                    case "bye":
                        status = false;
                        break;
                    case "list":
                        new ResponePrinter(taskList.toString()).print();
                        break;
                    case "todo":
                        description = inputTxt.substring(inputTxt.indexOf(' ')).trim();
                        taskId = taskList.addToDo(description);
                        new ResponePrinter(Templates.addTaskMsg((taskList.getTask(taskId)).toString(), taskId + 1)).print();
                        break;
                    case "deadline":
                        formatInputTxt = inputTxt.split("/");
                        description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' ')).trim();
                        due = formatInputTxt[1].substring(formatInputTxt[1].indexOf(' ')).trim();
                        taskId = taskList.addDeadline(description, due);
                        new ResponePrinter(Templates.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1)).print();
                        break;
                    case "event":
                        formatInputTxt = inputTxt.split("/");
                        description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' ')).trim();
                        due = formatInputTxt[1].substring(formatInputTxt[1].indexOf(' ')).trim();
                        taskId = taskList.addEvent(description, due);
                        new ResponePrinter(Templates.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1)).print();
                        break;
                    case "done":
                        formatInputTxt = inputTxt.split(" ");
                        taskId = Integer.parseInt(formatInputTxt[1]) - 1;
                        if (taskId > taskList.getTotalTasks() || taskId < 0) {
                            throw new DukeException(Templates.invalidTaskIdMsg);
                        } else {
                            taskList.completeTask(taskId);
                            new ResponePrinter(Templates.completeTaskMsg(taskList.getTask(taskId).toString())).print();
                        }
                        break;
                    default:
                        throw new DukeException(Templates.invalidCommandMsg);
                }
            } catch (DukeException e) {
                new ResponePrinter(e.getMessage()).print();
            } catch (NumberFormatException e) {
                new ResponePrinter(Templates.invalidTaskInputMsg).print();
            } catch (IllegalArgumentException e) {
                new ResponePrinter(Templates.invalidCommandMsg).print();
            } catch (ArrayIndexOutOfBoundsException e) {
                new ResponePrinter(Templates.invalidFormatMsg).print();
            }
        }

            // Print Exiting Message
            new ResponePrinter(Templates.exitMsg).print();
        }
}
