import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    private void storeTask(String name) {
        System.out.println("Added: " + name);
        Task newTask = new Task(name);
        taskList.add(newTask);
    }

    private void printList() {
        int counter = 1;
        for(Task task : taskList) {
            System.out.println(counter + ". " + task.name + " " + task.getStatusIcon());
            counter++;
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");

        String command = sc.nextLine();  // Read user input
        while (!command.equals("bye")) {
            String arr[] = command.split(" ", 2);
            if(command.equals("list")) {
                chatbot.printList();
            } else if (arr[0].equals("done")) {
                int n = Integer.parseInt(arr[1]);
                Task temp = chatbot.taskList.get(n - 1);
                temp.checkTask();
                System.out.println("Woohoo i've checked off this task" + "\n" + temp.name + " " + temp.getStatusIcon());
            } else {
                chatbot.storeTask(command);
            }
            String nextCommand = sc.nextLine();
            command = nextCommand;
        }
        System.out.println("Bye, Have an awesome day!");
    }
}

