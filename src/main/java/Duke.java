import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Response.hello();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                Response.bye();
                break;
            } else if (userInput.equals("list")) {
                Response.listTasks();
            } else if (userInput.contains("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Response.markAsDone(taskNumber);
            } else {
                Response.addTask(userInput);
            }
        }
    }
}
