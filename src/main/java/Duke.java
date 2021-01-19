import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void storeTask(Task task) {
        taskList.add(task);
        System.out.println("Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    public void printList() {
        String msg = "Here are the tasks in your list:";
        for(int i = 0; i < taskList.size(); i++) {
            msg+= "\n" +  (i + 1) + ". " + taskList.get(i);
        }
        System.out.println(msg);
    }

    public void markComplete(int n) {
        Task temp = this.taskList.get(n - 1);
        temp.checkTask();
        System.out.println("Duchess: Woohoo I've checked off this task:" + "\n" + temp.toString());

    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Duchess: Hello, Duchess here. What can i do for you?");

        String command = sc.nextLine();  // Read user input
        while (!command.equals("bye")) {
            String arr[] = command.split(" ", 2);
            String action = arr[0];
            if(command.equals("list")) {
                chatbot.printList();
            } else if (action.equals("done")) {
                int n = Integer.parseInt(arr[1]);
                chatbot.markComplete(n);
            } else if (action.equals("todo")) {
                Task todo = new Todo(arr[1]);
                chatbot.storeTask(todo);
            } else if(action.equals("event")) {
                System.out.println("Duchess: When will this event be?");
                String date = sc.nextLine();
                Task event = new Event(arr[1], date);
                chatbot.storeTask(event);
            } else if(action.equals("deadline")) {
                System.out.println("Duchess: When does this have to be done by?");
                String due = sc.nextLine();
                Task deadline = new Deadline(arr[1], due);
                chatbot.storeTask(deadline);
            }
            String nextCommand = sc.nextLine();
            command = nextCommand;
        }
        System.out.println("Bye, Have an awesome day!");
    }
}

