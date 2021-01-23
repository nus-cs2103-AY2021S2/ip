import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
        System.out.println("");
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int inputLength = input.split(" ").length;
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            try {
                Parser.manage(input, taskManager);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("");
            }
            if(sc.hasNextLine()) {
                input = sc.nextLine();
                inputLength = input.split(" ").length;
                command = input.split(" ")[0];
            } else {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
