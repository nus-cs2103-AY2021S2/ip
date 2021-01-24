package fakebot;

import fakebot.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanf;

    public Ui() {
        scanf = new Scanner(System.in);
    }

    private static String DIVIDER = "____________________________________________________________\n";

    //Print Standard Message
    public void printBotMessage(String message) {
        String printMessage = DIVIDER + message + "\n" + DIVIDER;
        print(printMessage);
    }

    //Print List of String
    public void printList(String startingMessage, List<String> messages) {
        StringBuilder printMessage = new StringBuilder(DIVIDER);
        printMessage.append(startingMessage);
        for (int i = 1; i <= messages.size(); i++) {
            printMessage.append(i);
            printMessage.append(".");
            printMessage.append(messages.get(i - 1));
            printMessage.append("\n");
        }
        printMessage.append(DIVIDER);
        print(printMessage.toString());
    }

    //Print List of Tasks
    public void printTasks(TaskList taskList) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            messages.add(taskList.getTask(i).toString());
        }
        printList("Here are the tasks in your list:\n", messages);
    }

    //Print Message,
    private void print(String message) {
        System.out.println(message);
    }

    //Read Line
    public String readLine() {
        String input = scanf.nextLine();
        return input;
    }
}
