package kobe;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Ui {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";
    public static ArrayList<String> responses = new ArrayList<>();;

    /**
     * Constructor for the Ui object.
     */
    public Ui() {
//        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);
    }

    /**
     * To print a line with indentation.
     */
    void showLine() {
        System.out.println(line);
    }

    /**
     * To print a line with indentation.
     *
     * @return  a line with indentation
     */
    String line() {
        return line;
    }

    /**
     * To print a line with indentation.
     *
     * @return  a line with indentation
     */
    String ind() {
        return ind;
    }

    /**
     * To get the most recent response for Kobe, in order to reply
     * to the user's most recent input.
     *
     * @return  Kobe's response
     */
    public static String getMostRecentResponse(){
        return responses.get(responses.size() - 1);

    }

    public static void addIncompleteDescriptionResponse() {
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like you have yet to complete your request.\n" +
                "Type help for more info");
    }

    public static void addIncorrectDescriptionResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command is incorrect.\n" +
                "Type help for more info");
    }

    public static void addWhitespaceResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command has too much empty spacing.\n" +
                "Type help for more info");
    }

    public static void addGoodbyeResponse(){
        responses.add("Goodbye! Kobe has saved your list.\n" +
                "Kobe hopes to see you soon!");
    }

    public static void addShowTaskListResponse(TaskList tasks){
        String fullResponse = "";
        fullResponse += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            fullResponse += ind + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        responses.add(fullResponse);
        System.out.println(line + fullResponse + line);
    }

    public static void addAddDateTaskResponse(Task currentTask, int size){
        String fullResponse = "";
        fullResponse += "Got it! Kobe marked down this date!\n";
        fullResponse += ind + "Kobe added this task:\n" + ind + ind +
                currentTask + "\n";
        fullResponse += ind + "Kobe sees that you have " +
                size + " task(s) in the list.\n";
        responses.add(fullResponse);

        System.out.print(line + "Got it! Kobe marked down this date!\n");
        System.out.println(ind + "Kobe added this task:\n" + ind + ind +
                currentTask);
        System.out.println(ind + "Kobe sees that you have " +
                size + " task(s) in the list.\n" + line);
    }

    public static void addAddNormalTaskResponse(Task currentTask, int size){
        String fullResponse = "";
        fullResponse += ind + "Got it! Kobe added this task:\n" + ind + ind +
                currentTask +"\n";
        fullResponse += ind + "Kobe sees that you have " +
                size + " task(s) in the list.\n";
        responses.add(fullResponse);

        System.out.println(line + "Got it! Kobe added this task:\n" + ind + ind +
                currentTask);
        System.out.println(ind + "Kobe sees that you have " +
                size + " task(s) in the list.\n" + line);
    }

    public static void addCompleteTaskResponse(int taskNumber, Task currentTask) {
        String fullResponse = "";
        fullResponse += "Nice work! Kobe will mark your task as done!\n";
        fullResponse += ind + currentTask;
        responses.add(fullResponse);

        System.out.print(line + "Nice work! Kobe will mark your task as done!\n" + ind);
        System.out.println(ind + currentTask + "\n" + line);

    }

    /**
     * To display an error message in the context of Kobe
     */
    void showLoadingError() {
        //"No prior saved data!"
        System.out.println(line + "Kobe detected no prior saved data!\n" + line);
    }

}