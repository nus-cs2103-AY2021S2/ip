import java.util.ArrayList;

public final class Templates {

    public Templates() {

    }

    // Constant Declarations
    public static final String greetingMsg = "Hello! I'm DatoDato! Your personal helper bot. :) \n" +
            "What can I do for you?";

    public static final String exitMsg = "Bye! Checkout another cool bot @KatoKatoBot on Telegram.\n" +
            "Hope to see you again soon!";

    public static final  String emptyInputMsg = "☹ OOPS!!! I'm sorry, input cannot be empty!";

    public static final  String invalidTaskInputMsg = "☹ OOPS!!! I'm sorry, taskId should be a number. " +
            "You can use the list command to find the tasks.";

    public static final String invalidCommandMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means!";

    public static final String invalidTaskIdMsg = "☹ OOPS!!! I'm sorry, task not found! Please select a task in " +
            "range. You can use the list command to find the tasks.";

    public static final String invalidFormatMsg = "☹ OOPS!!! I'm sorry, your formatting for your message isn't right!";

    public static String taskListMsg(ArrayList<Task> tasks) {
        StringBuffer result = new StringBuffer("Here are the tasks in your list: \n");
        for (int i = 1; i <= tasks.size(); i++) {
            result.append(String.format("%d.%s", i, tasks.get(i-1)));
            if (i != tasks.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static String completeTaskMsg(String description) {
        return "Nice! I've marked this task as done: \n  " + description;
    }

    public static String deleteTaskMsg(String description, int totalTask) {
        return String.format("Noted. I've removed this task: \n  %s\nNow you have %d tasks in the list.",
                description, totalTask);
    }

    public static String addTaskMsg(String description, int totalTask) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                description, totalTask);
    }

}
