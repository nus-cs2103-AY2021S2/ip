import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hey yo, I'm Echo.\nI'll echo your words for now.\n";
        String goodbye = "    Bye bye, catch you soon.";
        Scanner sc = new Scanner(System.in);
        String echo;

        System.out.println(greeting);

        echo = sc.nextLine();
        while(!echo.equals("bye")) {
            System.out.println("    " + echo + '\n');
            echo = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
