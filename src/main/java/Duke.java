import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String userInput = "";
        int index = 0;

        printSegment();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printSegment();

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            String[] arrOfUserInput = userInput.split(" ");
            switch (arrOfUserInput[0]) {
                case "list": {
                    printSegment();
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                    }
                    printSegment();
                    break;
                }
                case "done": {
                    int userIndex = Integer.parseInt(arrOfUserInput[1]);
                    tasks[userIndex - 1].markAsDone();
                    printSegment();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + tasks[userIndex - 1]);
                    printSegment();
                }
                case "bye":
                    break;
                default: {
                    Task task = new Task(userInput);
                    tasks[index] = task;
                    index++;
                    printSegment();
                    System.out.println("\t added: " + userInput);
                    printSegment();
                }
            }
        }

        printSegment();
        System.out.println("\tBye. Hope to see you again soon!");
        printSegment();
    }

    static void printSegment() {
        System.out.println("\t____________________________________________________________");
    }
}
