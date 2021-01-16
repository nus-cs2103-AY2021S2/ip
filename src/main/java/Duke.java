import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Start up greeting message
        String greetingMessage = "Hello! I'm a Chat bot and my name " +
                "is Joe \nHow may I help you?";
        System.out.println(formatMessage(greetingMessage));

        // Task list
        List<Task> taskList = new ArrayList<>();

        boolean isChatBotOnline = true;
        while (isChatBotOnline) {
            // Listen to input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Echoing the input
            if (input.equals("bye")) {
                String byeMessage = "Goodbye, hope you had a great time!";
                System.out.println(formatMessage(byeMessage));
                isChatBotOnline = false;
            } else if (input.equals("list")) {
                System.out.println(formatMessage(getTaskListString(taskList)));
            } else if (input.contains("done")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5));
                    int arrayNumber = taskNumber - 1;
                    Task task = taskList.get(arrayNumber);
                    String doneMessage = task.setDone();
                    System.out.println(formatMessage(doneMessage));
                } catch (NumberFormatException e) {
                    System.out.println(formatMessage(e +
                            "\nError! Invalid task number." +
                            "\nPlease input a valid task number!"));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e +
                            "\nError! Task number does not exist." +
                            "\nPlease input a valid task number!");
                }
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(formatMessage("Added: " + input));
            }
        }
    }


    public static String formatMessage(String str) {
        return "____________________________________________________________" +
                "\n" + str + "\n" +
                "____________________________________________________________\n";
    }

    // prints all of the tasks in the taskList
    public static String getTaskListString(List<Task> taskList) {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString + "\n";
        }
        return taskListString;
    }
}
