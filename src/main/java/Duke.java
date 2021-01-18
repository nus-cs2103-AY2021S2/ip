import main.java.Echo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        Echo DukeEchoer = new Echo();

        System.out.println(DukeEchoer.DukeResponse("Hello! I'm Duke\n" + "     What can I do for you?"));

        String userinput = scanner.nextLine();

        while ( !userinput.equals("bye")){
            System.out.println(DukeEchoer.DukeResponse(userinput));
            userinput = scanner.nextLine();
        }

        System.out.println(DukeEchoer.DukeResponse("Bye. Hope to see you again soon!"));
    }
}
