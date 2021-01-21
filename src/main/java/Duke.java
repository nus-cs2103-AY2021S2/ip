import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke \n    What can I do for you?");
        System.out.println("    ____________________________________");

        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int currIndex = 0;

        while (sc.hasNext()) {
            String command = sc.nextLine();
            System.out.println("    ____________________________________");

            if (command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________");
                break;
            } else if (command.equals("list")) {
                for (int i = 1; i <= currIndex; i++) {
                    System.out.printf("    %d.%s\n", i, list[i - 1]);
                }
            } else if (command.matches(".*done.*")) {
                // To get the index 
                int index = command.charAt(5) - 48;
                list[index - 1].markAsDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("        " + list[index - 1]);
            } else {
                list[currIndex] = new Task(command);
                System.out.println("    added: " + command);
                currIndex++;
            }

            System.out.println("    ____________________________________");
        }
        
        sc.close();
    }
}
