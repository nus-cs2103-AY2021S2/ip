package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String space = "     ";
    private static final String divider = "    -------------------------------------------->>>>>\n";
    public Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printWelcomeText() {
        System.out.println(divider + space + "(≧∇≦)ﾉ Konichiwa~ Watashi wa Duke desu! \n"
               + space + "What can I do for you today?\n" + divider);
    }

    public void printGoodbye() {
        System.out.println(divider + space + "Sayonara, matta ne~ \n" + divider);
    }

    public void printList(TaskList list) {
        String resultString = divider;
        if(list.size() == 0) {
            resultString = resultString + space + "Ara, the list is empty right now!\n";
        } else {
            resultString = resultString + space + "Hai~ Current tasks are: \n";
            for(int i = 0; i < list.size(); i++) {
                resultString = resultString + space + (i + 1) + ". " + list.getTask(i).toString() + "\n";
            }
        }
        resultString = resultString + divider;
        System.out.println(resultString);
    }


    public void printText(String text) {
        System.out.println(divider + space + text + "\n" + divider);
    }

    public void printCompletedMsg(Task task) {
        System.out.println(divider + space + "Otsukare! I've marked this task as done: \n"
               + space + "  " + task.toString() + "\n" + divider);
    }

    public void printDeleteMsg(Task task, int size) {
        System.out.println(divider + space + "Yayyyy! I've removed the task: \n" + space + "  "
                + task.toString() + "\n" + space + "Now you have " + size + " task(s) left!\n" + divider);
    }

    public void printTask(Task task, int size) {
        System.out.println(divider + space + "Ayyyyy I've added the task: \n"
                + space + "  " + task.toString() + "\n" + space
                + "Now you have " + size + " task(s) in the list.\n"+ divider);
    }

    public String nextCommand() {
        return sc.nextLine();
    }

    public boolean hasNextCommand() {
        return sc.hasNext();
    }

    public void showLoadingError() {
        System.out.println("Ehhh-- There is something wrong with the file!");
    }


}
