import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {
    private Scanner scanf;
    public IO(){
        scanf = new Scanner(System.in);
    }
    private static String DIVIDER = "____________________________________________________________\n";
    public void printBotMessage(String message){
        String printMessage = DIVIDER + message + "\n"+DIVIDER;
        print(printMessage);
    }

    public void printList(String startingMessage, List<String> messages){
        StringBuilder printMessage = new StringBuilder(DIVIDER);
        printMessage.append(startingMessage);
        for(int i = 1; i <= messages.size();i++) {
            printMessage.append(i);
            printMessage.append(".");
            printMessage.append(messages.get(i - 1));
            printMessage.append("\n");
        }
        printMessage.append(DIVIDER);
        print(printMessage.toString());
    }
    public void printTasks(List<Task> tasks){
        List<String> messages = new ArrayList<>();
        for (Task task: tasks) {
            messages.add(task.toString());
        }
        printList("Here are the tasks in your list:\n", messages);
    }
    private void print(String message) {
        System.out.println(message);
    }
    public String readLine(){
        String input = scanf.nextLine();
        return input;
    }
}
