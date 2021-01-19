import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?: ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Initialise list
        Task[] list = new Task[100];
        int numOfItems = 0;

        // User input
        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");
            if (input.equals("list")) {
                for (int i = 1; i <= numOfItems; i++) {
                    String output = String.format("%s. %s", i, list[i - 1].toString());
                    System.out.println(output);
                }
            } else if (inputArr[0].equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                list[Integer.parseInt(inputArr[1]) - 1].markAsDone();
                System.out.println(list[Integer.parseInt(inputArr[1]) - 1].toString());
            } else {
                list[numOfItems] = new Task(input);
                numOfItems++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}