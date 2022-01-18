import java.util.ArrayList;

public final class Templates {

    public Templates() {

    }

    // Constant Declarations
    public static final String greetingMsg = "Hello! I'm DatoDato! Your personal helper bot. :) \n" +
            "What can I do for you?";

    public static final String exitMsg = "Bye! Checkout another cool bot @KatoKatoBot on Telegram.\n" +
            "Hope to see you again soon!";

    public static final  String emptyInputMsg = "Input cannot be empty!";

    public static final  String invalidDoneMsg = "Tasks not found! Please select an task in range.";

    public static String taskListMsg(ArrayList<Task> tasks) {
        StringBuffer result = new StringBuffer("Here are the tasks in your list: \n");
        Task curr;
        for (int i = 1; i <= tasks.size(); i++) {
            curr = tasks.get(i-1);
            result.append(i);
            result.append(". [");
            result.append(curr.getStatusIcon());
            result.append("] ");
            result.append(curr);
            if (i != tasks.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static String completeTaskMsg(String description) {
        return "Nice! I've marked this task as done: \n" + "[X] " + description;
    }

    public static String addTaskMsg(String description) {
        return  "added: " + description;
    }

}
