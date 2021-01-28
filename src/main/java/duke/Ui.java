package duke;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public Ui() {
        textWarper("Hello! I'm Duke \nWhat can I do for you?");
    }

    public void showBye() {
        textWarper("Bye. Hope to see you again soon!\n");
    }

    public void showList(TaskList tasks) {
        String out = "Here are the tasks in your list: \n";
        out += printList(tasks);
        textWarper(out);
    }

    public void showFind(TaskList tasks, String toFind) {
        String out;
        List<Task> tempList = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.getName().contains(toFind)) {
                tempList.add(t);
            }
        }
        if (!tempList.isEmpty()) {
            out = "Here are the matching tasks in your list: \n";
            out += printList(new TaskList(tempList));
        } else {
            out = "Nothing in the list matches\n";
        }
        textWarper(out);
    }

    public void showDone(String input, TaskList tasks) {
        String out = "Nice! I've marked this task as done: \n  ";
        out += tasks.get(getIndex(input));
        textWarper(out);
    }

    public void showTaskAdded(TaskList tasks, Task temp) {
        String out = "Got it. I've added this task:\n ";
        out += temp.toString() + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() + 1);
        textWarper(out);
    }

    public void showDeleteTask(String input, TaskList tasks) {
        String out = "Noted. I've removed this task: \n  ";
        out += tasks.get(getIndex(input)) + "\n";
        out += String.format("Now you have %d tasks in the list.", tasks.size() - 1);
        textWarper(out);
    }

    public void showDukeTaskError() {
        textWarper("☹ OOPS!!! The description of a task cannot be empty.");
    }

    public void showDukeGeneralError() {
        textWarper("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showDukeEmptyListError() {
        textWarper("You have nothing in the list!");
    }

    public void showLoadingError() {
        textWarper("This file cant be loaded! Creating a new file called duke.txt in CWD");
    }

    public int getIndex(String input) {
        return Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
    }

    private String printList(TaskList contentList) {
        StringBuilder printStr = new StringBuilder();
        for (int i = 0; i < contentList.size(); i++) {
            String textToAdd = String.format("%d.%s%n", i + 1, contentList.get(i).toString());
            printStr.append(textToAdd);
        }
        return printStr.toString();
    }

    private void textWarper(String a) {
        System.out.println("____________________________________________________________");
        System.out.println(a);
        System.out.println("____________________________________________________________");
    }

}
