import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String horizon = "-------------------------------------";
    private static String[] taskList = new String[100];
    private static int countTask = 0;
    private static void printReply(String reply){
        System.out.println(indentation+horizon);
        switch (reply) {
            case "hello":
                System.out.println(indentation + "Hello! I'm Duke Y(^_^)Y");
                System.out.println(indentation+"What can I do for you?\n");
                break;
            case "bye":
                System.out.println(indentation + "Bye. (>_<) Hope to see you again soon! ");
                break;

            case "list":
                for (int i = 0; i < countTask; i++) {
                    System.out.println(indentation + (i+1) + "." + taskList[i]);
                }
                break;
            default:
                System.out.println(indentation + "added:" + reply);
            }
        System.out.println(indentation+horizon);
    }
    private static void taskAdd(String task){
        taskList[countTask] = task;
        countTask++;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String command = "";

        System.out.println("Hello from\n" + logo);
        printReply("hello");

        Scanner scanner = new Scanner(System.in);
        do {
            command = scanner.nextLine();
            if (!command.equals("bye") && !command.equals("list")){
                taskAdd(command);
            }
            printReply(command);
        }while(!command.equals("bye"));
    }
}
