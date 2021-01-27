package duke.ui;

import duke.duke.Duke;
import duke.tasks.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class Ui {
    public static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    public static final PrintWriter OUT = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void showWelcomeMessage(Duke bot) {
        OUT.printf("Hello from%n%s%n", bot.getLogo());
        OUT.println("Hello! I'm Duke");
        OUT.println("What can I do for you?");

        for (int i = 0; i < 50; i++) {
            OUT.print('\u2500');
        }
        OUT.println();
        OUT.flush();
    }

    public static void askForUserRequest() {
        OUT.println("Anything else?");
        OUT.flush();
    }

    public static void showTasksToUser(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            OUT.printf("%d.%s%n", i + 1, list.get(i));
        }
        OUT.flush();
    }

    public static void printEmptyLine() {
        OUT.println();
        OUT.flush();
    }

    public static void showAddTaskMessage(Task task, List<Task> list) {
        OUT.println("Got it. I've added this task:");
        OUT.printf(" %s%n", task);
        OUT.printf("Now you have %d tasks in the list.%n", list.size());
        OUT.flush();
    }

    public static void showRemoveTaskMessage(Task task, List<Task> list) {
        OUT.println("Noted. I've removed this task:");
        OUT.printf(" %s%n", task);
        OUT.printf("Now you have %d tasks in the list.%n", list.size());
        OUT.flush();
    }

    public static void showDoneTaskMessage(List<Task> list, int id) {
        OUT.println("Nice! I've marked this task as done:");
        OUT.printf(" %s%n", list.get(id));
        OUT.flush();
    }

    public static void showExitMessage() {
        OUT.println("Bye. Hope to see you again soon!");
        OUT.flush();
        OUT.close();
    }

    public static void print(String message) {
        OUT.println(message);
        OUT.flush();
    }

    public static String readUserInput() throws IOException {
        return IN.readLine();
    }
}
