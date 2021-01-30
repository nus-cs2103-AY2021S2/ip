package soonwee.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static final int TODO = 1;
    public static final int DEADLINE = 2;
    public static final int EVENT = 3;
    public static final int FIND = 4;

    public Parser() {

    }

    public int checkTaskType(String data) {
        if (data.indexOf("todo") == 0 && data.contains("todo")) {
            return TODO;
        } else if (data.indexOf("deadline") == 0 && data.contains("deadline")) {
            return DEADLINE;
        } else if (data.indexOf("event") == 0 && data.contains("event")) {
            return EVENT;
        } else {
            return -1;
        }
    }

    //Checking front command
    public String checkFrontInput(String cmd, int taskID) {
        String task = new String();
        String type = new String();
        try {
            if (taskID == TODO || taskID == FIND) {
                type = "todo";
                if (cmd.length() > 4) {
                    task = cmd.substring(5);
                }
            } else if (taskID == DEADLINE) {
                type = "deadline";
                int seg = cmd.indexOf("/");
                if (cmd.length() > 8 && seg != -1) {
                    task = cmd.substring(9, seg);
                }
            } else if (taskID == EVENT) {
                type = "event";
                int seg = cmd.indexOf("/");
                if (cmd.length() > 5 && seg != -1) {
                    task = cmd.substring(6, seg);
                }
            }
            if (task.equals("") && !type.isEmpty()) {
                throw new DukeException(" ☹ OOPS!!! " + "The description of a " + type + " cannot be empty.");
            } else if (task.equals("") && type.isEmpty()) {
                throw new DukeException("Please input the correct command.");
            }
        } catch (DukeException de) {
            System.out.println(de.getMessage());
        }
        return task;
    }

    public LocalDateTime dateFormatter(String cmd) {
        int nextChar = -1;
        int nextWord = 4;
        if (cmd.indexOf("/by") != -1) {
            nextChar = cmd.indexOf("/by");
        } else if (cmd.indexOf("/at") != -1) {
            nextChar = cmd.indexOf("/at");
        }
        String date = cmd.substring(nextChar + nextWord);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        LocalDateTime formatDate = LocalDateTime.parse(date, formatter);
        return formatDate;
    }
}