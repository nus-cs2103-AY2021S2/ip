import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<String> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    private void storeTask(String task) {
        System.out.println("Added: " + task);
        taskList.add(task);
    }

    private void printList() {
        int counter = 1;
        for(String task : taskList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");

        String command = sc.nextLine();  // Read user input
        while (!command.equals("bye")) {
            if(command.equals("list")) {
                chatbot.printList();
            } else {
                chatbot.storeTask(command);
            }
            String nextCommand = sc.nextLine();
            command = nextCommand;
        }
        System.out.println("Bye, Have an awesome day!");
    }
}

