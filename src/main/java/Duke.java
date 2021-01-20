import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String seperator = "------------------\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Parrot mode engaged");
        Scanner in = new Scanner(System.in);
        String command = "";
        List<String> store = new ArrayList<>();
        do{
            System.out.print(seperator + "Listening to your input: ");
            command = in.nextLine();
            switch(command){
                case "bye":
                    System.out.println(seperator + "Goodbye from\n" + logo);
                    break;
                case "list":
                    for (int i = 0; i < store.size(); i++) {
                        System.out.println("Entry " + String.valueOf(i+1) + ": " + store.get(i));
                    }
                    break;
                default:
                    store.add(command);
                    System.out.println("Added: " + command);
            }
        }while(!command.equals("bye"));
    }
}
