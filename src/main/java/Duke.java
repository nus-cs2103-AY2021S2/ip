import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------");
        System.out.println("Hi! I'm Timmy!\nWhat can Timmy do for you today?");
        System.out.println("------------------------------------------------");
        String input;
        String[] items = new String[100];
        int i = 0;
        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                System.out.println("------------------------------------------------");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ". " + items[j]);
                }
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("------------------------------------------------");
                items[i] = input;
                i++;
                System.out.println("added: " + input);
                System.out.println("------------------------------------------------");
            }
        };
        sc.close();
        System.out.println("------------------------------------------------");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("------------------------------------------------");
    }
}
