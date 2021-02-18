/*
 * Deal with interactions with the users.
 */
public class Ui {

    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo + "What can I do for you?";
        System.out.println(greeting);
        return greeting;
    }

    public String giveList(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    public String addTaskReply(String task, String numOfTasks) {
        return "Got it. I've added this task:\n" + task + "\n" + "Now you have " + numOfTasks + " tasks in the list.";
    }

    public String doneReply(String task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String deleteReply(String task, String numOfTasks) {
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + numOfTasks + " tasks in the list.";
    }

    public String noDescReply() {
        return "The description cannot be empty.";
    }

    public String noDateReply() {
        return "The date cannot be empty.";
    }

    public String noTimeReply() {
        return "The time cannot be empty.";
    }

    public String noLineReply() {
        return "The line number cannot be empty.";
    }

    public String wrongLineReply() {
        return "Invalid line number.";
    }

    public String foundReply(String tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }

    public String notFoundReply() {
        return "Your list does not contain this word.";
    }

    public String confusedReply() {
        return "I'm sorry, but I do not know what that means.";
    }

    public String bidFarewell() {
        return "Goodbye! Hope to see you again soon!";
    }
}
