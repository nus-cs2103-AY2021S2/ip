import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String logo;
    public static void main(String[] args) {
        logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|_/ \\,||\\\\___|\n";
        greetUser();
        echo();
    }
    private static void greetUser() {
        System.out.println("Hello from\n" + logo);
    }
    private static void byeUser() {
        System.out.println("Bye from\n" + logo);
    }
    private static void echo() {
        while(true) {
            String cmd = sc.nextLine();
            if(cmd.equals("bye")) break;
            System.out.println(cmd);
        }
        byeUser();
    }
}
