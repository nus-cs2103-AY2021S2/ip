import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?: ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        sc.close();
        return;
    }
}