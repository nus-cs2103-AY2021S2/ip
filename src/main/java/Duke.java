import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Constant Declarations
        final String greetingMsg = "Hello! I'm DatoDato! Your personal helper bot. :) \n" +
                "What can I do for you?";
        final String exitMsg = "Bye! Checkout another cool bot @KatoKatoBot on Telegram.\n" +
                "Hope to see you again soon!";
        final Scanner sc = new Scanner(System.in);

        // Variable Declarations
        TaskList taskList = new TaskList();
        String inputTxt;
        boolean status = true;


        // Print Welcome Message
        new ResponePrinter(greetingMsg).print();

        while(status) {
            inputTxt = sc.nextLine();
            switch(inputTxt) {
                case "bye":
                    status = false;
                    break;
                case "list":
                    new ResponePrinter(taskList.getTaskList()).print();
                    break;
                default:
                    taskList.addTask(inputTxt);
                    new ResponePrinter("added: " + inputTxt).print();
                    break;
            }
        }

        // Print Exiting Message
        new ResponePrinter(exitMsg).print();

    }
}
