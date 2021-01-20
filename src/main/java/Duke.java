import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final StringBuilder lengthOfChatBox = new StringBuilder();
    private final ArrayList<Task> taskList = new ArrayList<>();

    private void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        lengthOfChatBox.append("-".repeat(50));
        lengthOfChatBox.append("\n");
    }

    private void formatInChatBox(String s) {
        System.out.println(lengthOfChatBox + s + lengthOfChatBox);
    }

    public void init() {
        setLengthOfChatBox();
        String greeting = "Hello I'm Mike\nWhat can I do for you?\n";
        formatInChatBox(greeting);
    }

    public void list() {
        int numOfText = taskList.size();
        StringBuilder output = new StringBuilder();

        if (taskList.isEmpty()) {
            output.append("No tasks saved\n");
        } else {
            int n = 1;
            output.append("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                output.append(i + 1).append(".").append(currentTask).append("\n");
            }
        }
        formatInChatBox(output.toString());
    }

    public void add(String s) {
        taskList.add(new Task(s));
        String output = "Added: " + s;
        formatInChatBox(output);
    }

    public void mark(int index) {
        Task taskToBeMarked = taskList.get(index-1);
        taskToBeMarked.markAsDone();
        formatInChatBox("Nice! I've marked this task as done: \n" + taskToBeMarked);
    }

    public boolean chat(String s, Duke d) {
        if (s.toLowerCase().equals("bye")) {
            String goodbyeMessage = "Bye. Hope to see you again soon!\n";
            formatInChatBox(goodbyeMessage);
            return false;
        } else if (s.length() > 5 && s.substring(0, 5). equals("done ")) {
            int index = Integer.parseInt(s.substring(5));
            d.mark(index);
        } else if (s.toLowerCase().equals("list")) {
            d.list();
        } else {
            d.add(s);
        }
        return true;
    }

    public static void main(String[] args) {
        Duke mike = new Duke();
        mike.init();
        Scanner sc = new Scanner(System.in);
        Boolean isChatting = true;

        while (isChatting) {
            String input = sc.nextLine();
            isChatting = mike.chat(input, mike);
        }
        sc.close();
    }
}
