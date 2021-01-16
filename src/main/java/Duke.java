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
            }

            if (userInput.equals("list")) {
                Response.listTasks();
            } else if (userInput.contains("done")) {
                int taskNumber = Integer.parseInt(userInput.substring(5));
                Response.markAsDone(taskNumber);
            } else if (userInput.contains("todo")) {
                Task currentTask = new Todo(userInput.substring(5));
                Response.addTask(currentTask);
            } else if (userInput.contains("deadline")) {
                int temp = userInput.indexOf('/') - 1;
                Task currentTask = new Deadline(userInput.substring(9, temp),
                        userInput.substring(temp + 4));
                Response.addTask(currentTask);
            } else if (userInput.contains("event")) {
                int temp = userInput.indexOf('/') - 1;
                Task currentTask = new Event(userInput.substring(6, temp),
                        userInput.substring(temp + 4));
                Response.addTask(currentTask);
            } else {
                System.out.println("Error\n");
            }
        }
    }
}
