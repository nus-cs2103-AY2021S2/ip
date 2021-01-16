import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static String OLDLOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String LOGO =
              " ______      _  ________   ____   ____ _______ \n"
            + "|  ____/ \\  | |/ /  ____| |  _ \\ / __ \\__   __|\n"
            + "| |__ /  \\  | ' /| |__    | |_) | |  | | | |   \n"
            + "|  __/ /\\ \\ |  < |  __|   |  _ <| |  | | | |\n"
            + "| | / ____ \\| . \\| |____  | |_) | |__| | | |\n"
            + "|_|/_/    \\_\\_|\\_\\______| |____/ \\____/  |_|\n";


    private static String EXITCOMMAND = "bye";
    private static String LISTCOMMAND = "list";
    private static String DONECOMMAND = "done";

    public static void main(String[] args) {
        printHelloMessage();

        List<Task> Tasks = new ArrayList<Task>();
        while(true){
            String reply = IO.readLine();
            if(reply.equals(EXITCOMMAND)) {
                break;
            }else if(reply.equals(LISTCOMMAND)) {
                IO.printTasks(Tasks);
            }else if(reply.startsWith(DONECOMMAND)){
                IO.printBotMessage(reply.substring(5));
                Integer index = Integer.parseInt(reply.substring(5));
                Tasks.get(index-1).markCompleted();
                printDoneMessage(Tasks.get(index-1));
            }else {
                Tasks.add(new Task(reply));
                IO.printBotMessage("added: "+reply);

            }
        }
        IO.printBotMessage("Bye. Hope to see you again soon!");
    }

    public static void printHelloMessage(){
        IO.printBotMessage("Hello from\n" + LOGO +"What can I do for you?");
    }

    public static void printDoneMessage(Task task){
        IO.printBotMessage("Nice! I've marked this task as done:\n" + task.toString());
    }
}
