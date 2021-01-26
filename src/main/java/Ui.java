import java.util.ArrayList;

public class Ui {
    public static String tab = "     ";
    public static String line = "     ............................................................";

    public Ui() { }

    public void printIntro() {
        String logo =
                " _____   _   _\n"
                        + "| ____| | | | |\n"
                        + "| |___  | | | | __   __\n"
                        + "|  ___| | | | | \\ \\ / /\n"
                        + "| |___  | | | |  \\ v /\n"
                        + "|_____| |_| |_|  /  /\n"
                        + "                /__/\n";

        System.out.println("   C H A T   W I T H\n" + logo);

        System.out.println(line + "\n"
                + tab + "Hi there! I'm Elly.\n"
                + tab + "How can I help you today?\n"
                + line);
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printList(ArrayList<Task> tasks, int numTasks) {
        System.out.println(tab + "Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            int num = i + 1;
            Task task = tasks.get(i);
            System.out.println(
                    tab + num + "." + task.toString());
        }
    }

    public void printDone(ArrayList<Task> tasks, int index) {
        System.out.println(tab + "Nice! I've marked this task as done:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public void printDelete(String string) {
        System.out.println(tab + "Noted. I've removed this task:");
        System.out.println(tab + string);
    }

    public void printAdd(ArrayList<Task> tasks, int index) {
        System.out.println(tab + "Got it. I've added this task:");
        System.out.println(tab + tasks.get(index).toString());
    }

    public void printNumTasks(int numTasks) {
        System.out.println(tab + "Now you have " + numTasks + " tasks in the list.");
    }

    public void printBye() {
        System.out.println(tab + "Goodbye, can't wait to see you again!");
        System.out.println(line);
    }

    public void printEmptyDescError(String task) {
        System.out.println(tab + "Oops! Description of " + task + " cannot be empty.");
    }

    public void printIdkError() {
        System.out.println(tab + "I'm sorry, I'm not sure what that means.");
    }
}
