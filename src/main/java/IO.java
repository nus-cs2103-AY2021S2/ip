import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO {
    private static String DIVIDER = "____________________________________________________________\n";
    public static void printBotMessage(String message){
        String printMessage = DIVIDER + message + "\n"+DIVIDER;
        print(printMessage);
    }
    public static void printList(List<String> messages){
        StringBuilder printMessage = new StringBuilder(DIVIDER);

        for(int i = 1; i <= messages.size();i++) {
            printMessage.append(i);
            printMessage.append(".");
            printMessage.append(messages.get(i - 1));
            printMessage.append("\n");
        }
        printMessage.append(DIVIDER);
        print(printMessage.toString());
    }
    public static void printTasks(List<Task> tasks){
        List<String> messages = new ArrayList<>();
        for (Task task: tasks) {
            messages.add(task.toString());
        }
        printList(messages);
    }
    private static void print(String message) {
        System.out.println(message);
    }
    public static String readLine(){
        Scanner scanf = new Scanner(System.in);
        String input = scanf.nextLine();
        return input;
    }
}
