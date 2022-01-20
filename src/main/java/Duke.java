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

        while (status && sc.hasNext()) {
            inputTxt = sc.nextLine();
            try {
                if (inputTxt.isEmpty())
                    throw new DukeException(Templates.emptyInputMsg);
                command = inputTxt.split(" ")[0].toUpperCase();

                Action action = Action.valueOf(command);
                switch (action) {
                    case EXIT:
                        status = false;
                        break;
                    case LIST:
                        if (taskList.isEmpty()) {
                            new ResponePrinter(Templates.noTaskMsg).print();
                        } else {
                            new ResponePrinter(taskList.toString()).print();
                        }
                        break;
                    case TODO:
                        description = inputTxt.substring(inputTxt.indexOf(' ')).trim();
                        if (description.isEmpty())
                            throw new DukeException(Templates.emptyInputMsg);
                        taskId = taskList.addToDo(description);
                        new ResponePrinter(Templates.addTaskMsg((taskList.getTask(taskId)).toString(), taskId + 1)).print();
                        break;
                    case DEADLINE:
                        formatInputTxt = inputTxt.split(" /by ");
                        description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' ')).trim();
                        due = formatInputTxt[1].trim();
                        if (description.isEmpty() || due.isEmpty())
                            throw new DukeException(Templates.invalidFormatMsg);
                        taskId = taskList.addDeadline(description, due);
                        new ResponePrinter(Templates.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1)).print();
                        break;
                    case EVENT:
                        formatInputTxt = inputTxt.split(" /at ");
                        description = formatInputTxt[0].substring(formatInputTxt[0].indexOf(' ')).trim();
                        due = formatInputTxt[1].trim();
                        if (description.isEmpty() || due.isEmpty())
                            throw new DukeException(Templates.invalidFormatMsg);
                        taskId = taskList.addEvent(description, due);
                        new ResponePrinter(Templates.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1)).print();
                        break;
                    case DONE:
                        formatInputTxt = inputTxt.split(" ");
                        taskId = Integer.parseInt(formatInputTxt[1]) - 1;
                        if (taskList.isDone(taskId)) {
                            new ResponePrinter(Templates.taskAlreadyDoneMsg).print();
                        }
                        else if (taskId > taskList.getTotalTasks() || taskId < 0) {
                            throw new DukeException(Templates.invalidTaskIdMsg);
                        } else {
                            taskList.completeTask(taskId);
                            new ResponePrinter(Templates.completeTaskMsg(taskList.getTask(taskId).toString())).print();
                        }
                        break;
                    case DELETE:
                        formatInputTxt = inputTxt.split(" ");
                        taskId = Integer.parseInt(formatInputTxt[1]) - 1;
                        if (taskId > taskList.getTotalTasks() || taskId < 0) {
                            throw new DukeException(Templates.invalidTaskIdMsg);
                        } else {
                            Task task = taskList.getTask(taskId);
                            int totalTask = taskList.deleteTask(taskId);
                            new ResponePrinter(Templates.deleteTaskMsg(task.toString(), totalTask + 1)).print();
                        }
                        break;
                    default:
                        throw new DukeException(Templates.invalidCommandMsg);
                }
            } catch (DukeException e) {
                new ResponePrinter(e.getMessage()).print();
            } catch (NumberFormatException e) {
                new ResponePrinter(Templates.invalidTaskInputMsg).print();
            } catch (ArrayIndexOutOfBoundsException|StringIndexOutOfBoundsException e) {
                new ResponePrinter(Templates.invalidFormatMsg).print();
            } catch (IllegalArgumentException e) {
                new ResponePrinter(Templates.invalidTaskIdMsg).print();
            } catch (IndexOutOfBoundsException e) {
                new ResponePrinter(Templates.invalidTaskIdMsg).print();
            }
        }

            // Print Exiting Message
            new ResponePrinter(Templates.exitMsg).print();
        }
}
