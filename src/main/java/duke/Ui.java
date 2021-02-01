package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    protected String addTask(Task task, int size) {
        String output = Duke.line + "\n" + " Got it. I've added this task: \n"
                + task.printNew() + "\n Now you have " + size
                + " tasks in the list" + "\n" + Duke.line;
        Duke.print(output);
        return output; // for testing
    }

    protected String doTask(Task task) {
        return String.format(Duke.line + "\n Nice! I've marked this task as done: " +
                "\n [%s] [%s] %s" +
                "\n" + Duke.line, task.type(), task.status(), task.toString());
    }

    protected String delete(Task task, int size) {
        String deleted = "[" + task.type() + "]" + "[" + task.status() + "] " + task.toString();
        return String.format("%s\nNoted. I've removed this task: \n %s\nNow you have %d tasks in the list\n%s",
                Duke.line, deleted, size, Duke.line);
    }

    protected String bye() {
        return Duke.line + "\n" + " Bye. Hope to see you again soon!" + "\n" + Duke.line;
    }

    protected String listTasks(TaskList taskList) throws ArrayIndexOutOfBoundsException {
        String output = "";
        output += Duke.line + "\n";
        int i = 1;
        for (Task s : taskList.list) {
            output += String.format("%d.[%s][%s] %s \n", i, s.type(), s.status(), s.toString());
            i++;
        }
        output += Duke.line;
        return output; // for storage
    }

    protected String printListTasks(TaskList taskList) {
        String output = listTasks(taskList);
        return output;
    }

    protected String find(TaskList taskList, String currLine) {
        String output = "Here are the matching tasks in your list \n";
        String toFind = currLine.split("find")[1].strip();
        String[] tasksByLine = listTasks(taskList).split("\n");
        for (String line : tasksByLine) {
            if (line.contains(toFind)) {
                output += line + "\n";
            }
        }
        return output;
    }

}
