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
        String line;
        List<Task> store = new ArrayList<>();
        String tokens[];
        do{
            System.out.print(separator + "Listening to your input: ");
            line = in.nextLine();
            tokens = line.split(" ");
            switch(tokens[0]){
                case "bye":
                    System.out.println(separator + "Goodbye from\n" + logo);
                    break;
                case "list":
                    for (Task t: store) {
                        System.out.println(t.toString());
                    }
                    break;
                case "done":
                    int index = Integer.valueOf(tokens[1]) - 1;
                    Task t = store.get(index);
                    t.isDone = true;
                    System.out.println("The following task is now marked as done:\n" + t.toString());
                    break;
                default:
                    store.add(new Task(line,store.size()));
                    System.out.println("Added: " + line);
            }
        }while(!tokens[0].equals("bye"));
    }
}
