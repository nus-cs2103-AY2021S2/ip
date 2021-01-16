import jdk.jfr.Event;

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
    private static String TODOCOMMAND = "todo";
    private static String DEADLINECOMMAND = "deadline";
    private static String EVENTCOMMAND = "event";

    private static IO io;
    public static void main(String[] args) {
        io = new IO();
        printHelloMessage();

        List<Task> Tasks = new ArrayList<Task>();
        while(true){
            String reply = io.readLine();
            if(reply.equals(EXITCOMMAND)) {
                break;
            }else if(reply.equals(LISTCOMMAND)) {
                io.printTasks(Tasks);
            }else if(reply.startsWith(DONECOMMAND)){
                Integer index = Integer.parseInt(reply.substring(5));
                Tasks.get(index-1).markCompleted();
                printDoneMessage(Tasks.get(index-1));
            }else if(reply.startsWith(TODOCOMMAND)){
                ToDos newTask = new ToDos(reply.substring(TODOCOMMAND.length()+1));
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            }else if(reply.startsWith(DEADLINECOMMAND)){
                String message = reply.substring(DEADLINECOMMAND.length()+1);
                String[] messages = message.split(" /by ");
                Deadlines newTask = new Deadlines(messages[0], messages[1]);
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            }else if(reply.startsWith(EVENTCOMMAND)){
                String message = reply.substring(EVENTCOMMAND.length()+1);
                String[] messages = message.split(" /at ");
                Events newTask = new Events(messages[0], messages[1]);
                Tasks.add(newTask);
                printAddedTaskMessage(newTask, Tasks.size());
            }
        }
        io.printBotMessage("Bye. Hope to see you again soon!");
    }

    public static void printHelloMessage(){
        io.printBotMessage("Hello from\n" + LOGO +"What can I do for you?");
    }

    public static void printDoneMessage(Task task){
        io.printBotMessage("Nice! I've marked this task as done:\n " + task.toString());
    }

    public static void printAddedTaskMessage(Task task, int count){
        io.printBotMessage("Got it. I've added this task: \n  " + task.toString()+"\nNow you have "+count+" tasks in the list.");
    }
}
