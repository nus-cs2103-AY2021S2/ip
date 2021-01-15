import java.util.Scanner;

public class Duke {
    static String welcome = " __________________________ \n"
            + "|  HI! THIS IS             |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  What can I do for you?  |\n"
            + "|__________________________|\n";

    static String bye = " __________________________ \n"
            + "|  GOOD BYE!               |\n"
            + "|   ____        _          |\n"
            + "|  |  _ \\ _   _| | _____   |\n"
            + "|  | | | | | | | |/ / _ \\  |\n"
            + "|  | |_| | |_| |   <  __/  |\n"
            + "|  |____/ \\__,_|_|\\_\\___|  |\n"
            + "|                          |\n"
            + "|  Always with you.        |\n"
            + "|__________________________|\n";

    static String horizontalLine = "____________________________________________________________\n";



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(welcome);
        String command = sc.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            System.out.print(horizontalLine);
            while (command.length() > 60) {
                System.out.print(command.substring(0, 59) + "\n");
                command = command.substring(59);
            }
            System.out.print(command + "\n");
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }
        System.out.println(bye);
    }
}
