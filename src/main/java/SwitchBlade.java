import java.util.Scanner;

public class SwitchBlade {

    private static void echo(String input) {
        System.out.println("You said: " + input + "\n");
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm SwitchBlade and I aim to do everything you want me to do!");
        System.out.println(sb.toString());

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equalsIgnoreCase("bye")) {
            echo(input);
            input = sc.next();
        }

        System.out.println("See you soon!");
    }
}
