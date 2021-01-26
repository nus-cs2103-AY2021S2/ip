package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class Ui {
    private static final String space = "     ";
    private static final String divider = "    -------------------------------------------->>>>>\n";

    public String printWelcomeText() {
        return divider + space + "(≧∇≦)ﾉ Konichiwa~ Watashi wa Duke desu! \n"
               + space + "What can I do for you today?\n" + divider;
    }

    public String printGoodbye() {
        return divider + space + "Sayonara, matta ne~ \n" + divider;
    }

    public String printList(TaskList list) {
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
        return  resultString;
    }


    public String printText(String text) {
        return divider + space + text + "\n" + divider;
    }

    public String printCompletedMsg(Task task) {
        return divider + space + "Otsukare! I've marked this task as done: \n"
               + space + "  " + task.toString() + "\n" + divider;
    }

    public String printDeleteMsg(Task task, int size) {
        return divider + space + "Yayyyy! I've removed the task: \n"
                + space + "  " + task.toString() + "\n" + space + "Now you have " + size + " task(s) left!\n" + divider;
    }

    public String printTask(Task task, int size) {
        return divider + space + "Ayyyyy I've added the task: \n"
                + space + "  " + task.toString() + "\n" + space
                + "Now you have " + size + " task(s) in the list.\n"+ divider;
    }


}
