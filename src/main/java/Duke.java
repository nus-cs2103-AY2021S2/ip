import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Response.hello();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("bye, have a good day! :)");
                break;
            } else if (userInput.equals("list")) {
                Response.listTasks();
            } else {
                Response.addTask(userInput);
            }
        }
    }
}
