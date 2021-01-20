import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Till next time!");
                break;
            }
            else {
                System.out.println("___\n"+command+"\n___");
            }
        }
        sc.close();
    }
}
