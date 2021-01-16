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

    public static void main(String[] args) {
        IO.printBotMessage("Hello from\n" + LOGO +"What can I do for you?");

        List<String> Task = new ArrayList<String>();
        while(true){
            String reply = IO.readLine();
            if(reply.equals(EXITCOMMAND)) {
                break;
            }else if(reply.equals(LISTCOMMAND)) {
                IO.printList(Task);
            }else {
                Task.add(reply);
                IO.printBotMessage("added: "+reply);
            }
        }
        IO.printBotMessage("Bye. Hope to see you again soon!");
    }
}
