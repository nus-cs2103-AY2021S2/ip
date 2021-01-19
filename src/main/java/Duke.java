import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "    ____________________________________________________________\n";
        //initialising greeting to be printed and print greeting.
        String greeting = line
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + line;
        System.out.print(greeting);

        Scanner sc = new Scanner(System.in);
        String command = "";

        String[] toDo = new String[100];
        int currIdx = 0;
        //while loop to echo commands of the user
        command = sc.nextLine();
        while(!command.equals("bye")) {
            //if command is list, we print out the to-do list.
            if(command.equals("list")) {
                int index = 1;
                System.out.print(line);
                for(int i = 0; i < currIdx; i++) {
                    System.out.println(String.format("    %d. %s", i+1, toDo[i]));
                }
                System.out.print(line);
                index++;
            } else {
                toDo[currIdx++] = command;
                System.out.print(line + "    added: " + command + "\n" + line);
            }
            command = sc.nextLine();
        }

        //print out the bye message
        String byeMessage = line
                + "    Bye. Hope to see you again soon!\n"
                + line;
        System.out.print(byeMessage);
    }
}
