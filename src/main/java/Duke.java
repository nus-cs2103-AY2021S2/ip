import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "------------------\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Parrot mode engaged");
        Scanner in = new Scanner(System.in);
        String command;
        List<Task> store = new ArrayList<>();
        do{
            System.out.print(separator + "Listening to your input: ");
            command = in.nextLine();
            switch(command){
                case "bye":
                    System.out.println(separator + "Goodbye from\n" + logo);
                    break;
                case "list":
                    for (Task t: store) {
                        System.out.println(t.toString());
                    }
                    break;
                default:
                    store.add(new Task(command,store.size()));
                    System.out.println("Added: " + command);
            }
        }while(!command.equals("bye"));
    }
}
