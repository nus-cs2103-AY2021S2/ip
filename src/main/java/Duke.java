import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    _______________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");
        
        String command = sc.next();
        while (!command.equals("bye")) {
            sc.nextLine();
            System.out.println(line + "\n    " + command + "\n" + line + "\n");
            command = sc.next();
        }
        System.out.println(line + "\n    BYE AND HAVE A GOOD DAY. Beep boop.\n" + line);
        sc.close();
        return;
    }
}
