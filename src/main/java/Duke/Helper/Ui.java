package Duke.Helper;

import java.util.ArrayList;
import java.util.function.Consumer;

import Duke.Constant.Constants;
import Duke.Task.Task;

/**
 * A class handles interactions with the user.
 */
public class Ui {
    public static final String LINES = "____________________________________________________________";

    /**
     * Formats the response that will be printed out to the users.
     * @param response A string that needed to be formatted before printing out.
     */
    public void printResponse(String response) {
        Consumer<String> output = (s) -> System.out.println(LINES + '\n' + s + '\n' + LINES + '\n');
        output.accept(response);
    }

    /**
     * Returns the string containing the statistics about the tasks in the list.
     * @param stat The statistics about the tasks in the list.
     * @return A string version that is printed out for the user.
     */
    public String stats(int[][] stat) {
        StringBuilder output = new StringBuilder();
        int todo = stat[0][0] + stat[0][1];
        if (todo > 0) {
            if (todo == 1) {
                output.append("There is 1 todo task in the list").append('\n');
            } else {
                output.append("There are ").append(todo).append(" todo tasks in the list").append('\n');
            }
            if (stat[0][0] > 0) {
                output.append("    ").append(stat[0][0]).append(" not done todo task")
                        .append(stat[0][0] == 1 ? "" : "s").append('\n');
            }
            if (stat[0][1] > 0) {
                output.append("    ").append(stat[0][1]).append(" done todo task")
                        .append(stat[0][1] == 1 ? "" : "s").append('\n');
            }
        }
        int deadline = stat[1][0] + stat[1][1];
        if (deadline > 0) {
            if (deadline == 1) {
                output.append("There is 1 deadline in the list").append('\n');
            } else {
                output.append("There are ").append(todo).append(" deadlines in the list").append('\n');
            }
            if (stat[1][0] > 0) {
                output.append("    ").append(stat[1][0]).append(" not done deadline")
                        .append(stat[1][0] == 1 ? "" : "s").append('\n');
            }
            if (stat[1][1] > 0) {
                output.append("    ").append(stat[1][1]).append(" done deadline")
                        .append(stat[1][1] == 1 ? "" : "s").append('\n');
            }
        }
        int event = stat[2][0] + stat[2][1];
        if (event > 0) {
            if (event == 1) {
                output.append("There is 1 event in the list").append('\n');
            } else {
                output.append("There are ").append(todo).append(" events in the list").append('\n');
            }
            if (stat[2][0] > 0) {
                output.append("    ").append(stat[2][0]).append(" not done event")
                        .append(stat[2][0] == 1 ? "" : "s").append('\n');
            }
            if (stat[2][1] > 0) {
                output.append("    ").append(stat[2][1]).append(" done event")
                        .append(stat[2][1] == 1 ? "" : "s");
            }
        }
        return output.toString();
    }
}
