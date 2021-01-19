import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?: ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Initialise list
        String[] list = new String[100];
        int numOfItems = 0;

        while (!input.equals("bye")) {
            System.out.println("_________________________________________________________");
            if (input.equals("list")) {
                for (int i = 1; i <= numOfItems; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
            } else {
                list[numOfItems] = input;
                numOfItems++;
                System.out.println("added: " + input);
            }
            System.out.println("_________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("_________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________________");

        sc.close();
    }
}