import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        while(status) {
            inputTxt = sc.nextLine();

            if (inputTxt.equals("")) {
                new ResponePrinter(Templates.emptyInputMsg).print();
            } else {

                  command = inputTxt.split(" ")[0];

                switch(command) {
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
                            new ResponePrinter(Templates.invalidDoneMsg).print();
                        } else {
                            taskList.completeTask(taskId);
                            new ResponePrinter(Templates.completeTaskMsg(taskList.getTask(taskId).toString())).print();
                        }
                        break;
                    default:
                        new ResponePrinter(Templates.invalidCommandMsg).print();
                        break;
                }
            }
        }

        // Print Exiting Message
        new ResponePrinter(Templates.exitMsg).print();
    }
}
