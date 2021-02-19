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
        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);
        responses.add("Hello! I'm Kobe.\n What can I do for you?");
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

    /**
     * To add a GUI response indicating an incomplete description by the user.
     */
    public static void addIncompleteDescriptionResponse() {
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like you have yet to complete your request.\n" +
                "Type help for more info");
    }

    /**
     * To add a GUI response indicating an incorrect description by the user.
     */
    public static void addIncorrectDescriptionResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command is incorrect.\n" +
                "Type help for more info");
    }

    /**
     * To add a GUI response indicating an excess of whitespace in the user input.
     */
    public static void addWhitespaceResponse(){
        responses.add("Oh no! Kobe doesn't understand what you mean.\n" +
                "It looks like your command has too much empty spacing.\n" +
                "Type help for more info");
    }

    /**
     * To add a GUI response indicating a goodbye.
     */
    public static void addGoodbyeResponse(){
        responses.add("Goodbye! Kobe has saved your list.\n" +
                "Kobe hopes to see you soon!");
    }

    /**
     * To add a GUI response indicating all the task in the list currently.
     */
    public static void addShowTaskListResponse(TaskList tasks){
        String fullResponse = "";
        if (tasks.size() == 0) {
            fullResponse += "Kobe does not see any tasks currently in your list! \n";
        } else {
            fullResponse += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                fullResponse += ind + (i + 1) + ". " + tasks.get(i).toString() + "\n";
            }
        }
        responses.add(fullResponse);
        System.out.println(line + fullResponse + line);
    }

    /**
     * To add a GUI response indicating an addition of a task with a condition.
     */
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

    /**
     * To add a GUI response indicating an addition of a normal task (without a condition).
     */
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

    /**
     * To add a GUI response indicating the completion of a task.
     */
    public static void addCompleteTaskResponse(int taskNumber, Task currentTask) {
        String fullResponse = "";
        fullResponse += "Nice work! Kobe will mark your task as done!\n";
        fullResponse += ind + currentTask;
        responses.add(fullResponse);

        System.out.print(line + "Nice work! Kobe will mark your task as done!\n" + ind);
        System.out.println(ind + currentTask + "\n" + line);

    }

    /**
     * To add a GUI response indicating an empty task list.
     */
    public static void addEmptyTaskListResponse() {
        responses.add("Kobe sees no more tasks from the list!\n");
        System.out.print(line + "Kobe sees no more tasks from the list!\n" + line + "\n");
    }

    /**
     * To add a GUI response indicating the removal of a task from the task list.
     */
    public static void addRemoveTaskResponse(Task currentTask, int currentTaskListSize) {
        String fullResponse = "";
        fullResponse += "Okay! Kobe will remove this task from the list:\n";
        fullResponse += ind + currentTask + "\n";
        fullResponse += "Kobe sees that you now have " + currentTaskListSize + " task(s) in the list.";
        if (currentTaskListSize == 0) { //If it's now empty, inform them.
            fullResponse += ind + "Your list is now empty!\n";
        }
        responses.add(fullResponse);

        System.out.print(line + "Okay! Kobe will remove your task from the list:\n" + ind);
        System.out.println(ind + currentTask + "\n");
        System.out.println(ind + "Kobe sees that you now have " + currentTaskListSize + " task(s) in the list.");
        if (currentTaskListSize == 0) { //If it's now empty, inform them.
            System.out.print(ind + "Your list is now empty!\n" + line);
        }
    }

    /**
     * To add a GUI response indicating tasks that match the user's input when 'find' is called.
     */
    public static void addFindTaskResponse(String allMatchingTasks) {
        String fullResponse = "";
        fullResponse += ind + "Kobe has found these tasks in your list:\n";
        fullResponse += allMatchingTasks;
        responses.add(fullResponse);

        System.out.print(line + "Kobe has found these tasks in your list:\n");
        System.out.print(allMatchingTasks);
    }

    /**
     * To add a GUI response indicating all the queries that can be called by the user.
     */
    public static void addHelpResponse() {
        String fullResponse = "";
        fullResponse += "Here are some examples of commands you can ask Kobe to do:\n\n"
                + "todo Task_ABC\n"
                + "deadline Task_ABC\n"
                + "deadline Task_ABC /by 2021-02-15\n"
                + "event Task_ABC\n"
                + "event Task_ABC /at 2021-02-15\n"
                + "list\n"
                + "delete 1\n"
                + "done 1\n"
                + "find Task_ABC\n"
                + "bye";
        responses.add(fullResponse);

        System.out.print(line + fullResponse + "\n" + line);
    }

    public static void addDuplicateTaskResponse(Task taskThatAlreadyExists) {
        String fullResponse = "";
        fullResponse += ind + "Kobe has found a duplicate task in your list:\n";
        fullResponse += ind + ind + taskThatAlreadyExists + "\n";
        fullResponse += ind + "Kobe did not add your task.\n";
        fullResponse += ind + "To add your task, use a different title for your task.";
        responses.add(fullResponse);

        System.out.print(line + "Kobe has found a duplicate task in your list:\n");
        System.out.println(ind + ind + taskThatAlreadyExists);
        System.out.println(line + "Kobe did not add your task.");
        System.out.println(ind + "To add your task, use a different title for your task.\n");
    }

    /**
     * To display an error message in the context of Kobe.
     */
    void showLoadingError() {
        String fullResponse = "";
        fullResponse += "Kobe detected no prior saved data!\n";
        responses.add(fullResponse);
        //"No prior saved data!"
        System.out.println(line + "Kobe detected no prior saved data!\n" + line);
    }

}