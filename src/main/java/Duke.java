import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String s = "     ";
        String logo = s + " ____        _        \n"
                + s + "|  _ \\ _   _| | _____ \n"
                + s + "| | | | | | | |/ / _ \\\n"
                + s + "| |_| | |_| |   <  __/\n"
                + s + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    _______________________________________________________________________\n";
        System.out.println(line);
        System.out.println(s + "Hello from\n" + logo);
        System.out.println(s + "What can I do for you?");
        System.out.println(line);

        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();

        while (String.valueOf(userInput).toLowerCase().equals("bye") == false) {
            System.out.println(line);
            System.out.println(s + userInput);
            System.out.println(line);
            userInput = scan.nextLine();
        }
        scan.close();
        System.out.println(line);
        System.out.println(s + "Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
