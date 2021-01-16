import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Start up greeting message
        String greetingMessage = "Hello! I'm a Chat bot and my name " +
                "is Joe \nHow may I help you?";
        System.out.println(messageFormatter(greetingMessage));

        // Task list
        List<String> taskList = new ArrayList<>();

        boolean isChatBotOnline = true;
        while (isChatBotOnline) {
            // Listen to input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Echoing the input
            switch(input) {
                case "bye":
                    String byeMessage = "Goodbye, hope you had a great time!";
                    System.out.println(messageFormatter(byeMessage));
                    isChatBotOnline = false;
                    break;
                case "list":
                    System.out.println(messageFormatter(getTaskListString(taskList)));
                    break;
                default:
                    taskList.add(input);
                    System.out.println(messageFormatter("Added: " + input));
            }
        }
    }


    public static String messageFormatter(String str) {
        return "____________________________________________________________" +
                "\n" + str + "\n" +
                "____________________________________________________________\n";
    }

    // prints all of the tasks in the taskList
    public static String getTaskListString(List<String> taskList) {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString + "\n";
        }
        return taskListString;
    }
}
