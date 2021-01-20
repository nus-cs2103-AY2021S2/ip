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
        do{
            System.out.print(seperator + "Listening to your input: ");
            command = in.nextLine();
            System.out.println("Echo: "+command);
        }while(!command.equals("bye"));
        System.out.println(seperator + "Goodbye from\n" + logo);
    }
}
