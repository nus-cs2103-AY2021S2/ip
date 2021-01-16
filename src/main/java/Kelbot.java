import java.util.Scanner;

public class Kelbot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Kelbot\n" + "What can I do for you?";
        System.out.println(greeting);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye le Bye!");
    }
}
