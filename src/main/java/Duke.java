import javax.lang.model.type.NullType;
import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String horizon = "------------------------------------------------------";
    private static Task[] taskList = new Task[100];
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
                System.out.println(indentation + "Here are the tasks in your list:");
                for (int i = 0; i < Task.countTask; i++) {
                    System.out.println(indentation + (i+1) + "." + taskList[i].getTaskInfo());
                }
                break;
            default:
                System.out.println(indentation + "added:" + reply);
            }
        System.out.println(indentation+horizon);
    }

    private  static void printDoneReply(int done){
        System.out.println(indentation+horizon);
        System.out.println(indentation+"Nice! I've marked this task as done:");
        System.out.println(indentation + (done+1) + "." + taskList[done].getTaskInfo());
        System.out.println(indentation+horizon);
    }

    private static void taskAdd(String task){
         taskList[Task.countTask] = new Task(task);
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
            switch (command){
                case "bye":
                case "list":
                case "hello":
                    printReply(command);break;
                default:
                    String[] commandSplit = command.split(" ");
                    if (commandSplit[0].equals("done") && commandSplit.length == 2){
                        taskList[Integer.parseInt(commandSplit[1])-1].markAsDone();
                        printDoneReply(Integer.parseInt(commandSplit[1])-1);
                    }
                    else {
                        taskAdd(command);
                        printReply(command);
                    }

            }

        }while(!command.equals("bye"));
    }
}
