import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Constant Declarations
        final Scanner sc = new Scanner(System.in);

        // Variable Declarations
        TaskList taskList = new TaskList();
        String inputTxt;
        String[] formatInputTxt;
        String command;
        boolean status = true;

        // Print Welcome Message
        new ResponePrinter(Templates.greetingMsg).print();

        while(status) {
            inputTxt = sc.nextLine();

            if (inputTxt.equals("")) {
                new ResponePrinter(Templates.emptyInputMsg).print();
            } else {
                formatInputTxt = inputTxt.split(" ");
                command = formatInputTxt[0];
                switch(command) {
                    case "bye":
                        status = false;
                        break;
                    case "list":
                        new ResponePrinter(taskList.toString()).print();
                        break;
                    case "done":
                        int taskId = Integer.parseInt(formatInputTxt[1]) - 1;
                        if (taskId > taskList.getTotalTasks() || taskId < 0) {
                            new ResponePrinter(Templates.invalidDoneMsg).print();
                        } else {
                            taskList.completeTask(taskId);
                            new ResponePrinter(Templates.completeTaskMsg(taskList.getTask(taskId).toString())).print();
                        }
                        break;
                    default:
                        taskList.addTask(inputTxt);
                        new ResponePrinter(Templates.addTaskMsg(inputTxt)).print();
                        break;
                }
            }
        }

        // Print Exiting Message
        new ResponePrinter(Templates.exitMsg).print();
    }
}
