import java.util.Scanner;

public class Duke {

    public static void echo(String instruction) {
        System.out.println("     --------------------------------");
        System.out.println("     " + instruction);
        System.out.println("     --------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner s = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        String instruction = s.nextLine();
        while(!instruction.equals("bye")) {
            echo(instruction);
            instruction = s.nextLine();
        }
        echo("Bye. Cya");
    }
}
