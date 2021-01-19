import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<String> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void runDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        scan();
    }

    public void scan() {
        Scanner s = new Scanner(System.in);
        String instruction = s.nextLine();
        while(!instruction.equals("bye")) {
            echo(instruction);
            instruction = s.nextLine();
        }
        echo("Bye. Cya");
    }

    public static void echo(String instruction) {
        System.out.println("     --------------------------------");
        System.out.println("     " + instruction);
        System.out.println("     --------------------------------");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
