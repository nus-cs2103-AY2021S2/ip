import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        String userInput = "";
        int index = 0;

        printSegment();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printSegment();

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                printSegment();
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks[i]);
                }
                printSegment();
            } else if (!userInput.equals("bye")) {
                tasks[index] = userInput;
                index++;
                printSegment();
                System.out.println("\t added: " + userInput);
                printSegment();
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
