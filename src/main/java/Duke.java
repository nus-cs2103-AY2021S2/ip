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
        System.out.println("No unicode allowed");
        Scanner in = new Scanner(System.in);
        String line;
        List<Task> store = new ArrayList<>();
        String tokens[];
        do{
            System.out.print(separator + "Listening to your input: ");
            line = in.nextLine();
            tokens = line.split(" ",2);
            switch(tokens[0]){
                case "bye":
                    System.out.println(separator + "Goodbye from\n" + logo);
                    break;
                case "list":
                    for (int i = 0 ; i < store.size(); i++) {
                        System.out.println(formatOrderedPrint(store,i));
                    }
                    break;
                case "done":
                    int index = Integer.valueOf(tokens[1]) - 1;
                    Task t = store.get(index);
                    t.isDone = true;
                    System.out.println("The following task is now marked as done:\n" +
                            formatOrderedPrint(store,-1));
                    break;
                case "todo":
                    store.add(new ToDos(tokens[1]));
                    System.out.println("The following todo item has been added:\n" +
                            formatOrderedPrint(store,-1));
                    break;
                case "deadline":
                    tokens = tokens[1].split(" /by ",2);
                    if (tokens.length < 2){

                    }
                    store.add(new Deadline(tokens[0],tokens[1]));
                    System.out.println("The following deadline item has been added:\n" +
                            formatOrderedPrint(store,-1));
                    break;
                case "event":
                    tokens = tokens[1].split(" /at ",2);
                    store.add(new Event(tokens[0],tokens[1]));
                    System.out.println("The following event item has been added:\n" +
                            formatOrderedPrint(store,-1));
                    break;
                default:
                    break;
            }
        }while(!tokens[0].equals("bye"));
    }
    private static String formatOrderedPrint(List<Task> tasks, int i){
        final int size = tasks.size();
        while (i < 0){
            i += size;
        }
        while (i >= size){
            i -= size;
        }
        return "Entry " + (i+1) + "|" + tasks.get(i).toString();
    }
}
