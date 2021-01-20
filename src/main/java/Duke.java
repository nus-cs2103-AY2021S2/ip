import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final StringBuilder lengthOfChatBox = new StringBuilder();
    private final ArrayList<Task> taskList = new ArrayList<>();

    private void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        for (int i = 0; i < 50; i++) {
            lengthOfChatBox.append("-");
        }
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
                output.append(i + 1).append(". ").append(currentTask).append("\n");
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
        Task taskToBeMarked = taskList.get(index - 1);
        taskToBeMarked.markAsDone();
        formatInChatBox("Nice! I've marked this task as done: \n" + taskToBeMarked);
    }

    public void exit() {
        formatInChatBox("Bye. Hope to see you again soon!\n");
    }

    public void add(String task, String description) {
        if (task.equals("TODO")) {
            Task newToDo = new ToDo(description);
            taskList.add(newToDo);
            String output = "Got it. I've added this task:\n  " + newToDo + "\n Now you have "
                    + taskList.size() + " tasks in the list.";
            formatInChatBox(output);
        } else if (task.equals("DEADLINE")) {
            int endIndexOfDescription = description.indexOf("/by ");
            String deadlineDescription = description.substring(0, endIndexOfDescription);
            String deadline = description.substring(endIndexOfDescription + 4);
            Task newDeadline = new Deadline(deadlineDescription, deadline);
            taskList.add(newDeadline);
            String output = "Got it. I've added this task:\n  " + newDeadline + "\n Now you have "
                    + taskList.size() + " tasks in the list.";
            formatInChatBox(output);
        } else if (task.equals("EVENT")) {
            int endIndexOfDescription = description.indexOf("/at ");
            String eventDescription = description.substring(0, endIndexOfDescription);
            String eventTime = description.substring(endIndexOfDescription + 4);
            Task newEvent = new Event(eventDescription, eventTime);
            taskList.add(newEvent);
            String output = "Got it. I've added this task:\n  " + newEvent + "\n Now you have "
                    + taskList.size() + " tasks in the list.";
            formatInChatBox(output);
        }
    }

    public static void main(String[] args) {
        Duke mike = new Duke();
        mike.init();
        Scanner sc = new Scanner(System.in);
        Boolean isChatting = true;

        while (isChatting) {
            String input = sc.next();
            Command c = Command.valueOf(input.toUpperCase());
            switch (c) {
                case BYE:
                    mike.exit();
                    return;
                case LIST:
                    mike.list();
                    break;
                case DONE:
                    int i = sc.nextInt();
                    mike.mark(i);
                    break;
                case TODO:
                    String toDoDescription = sc.nextLine();
                    mike.add("TODO", toDoDescription);
                    break;
                case DEADLINE:
                    String deadlineDescription = sc.nextLine();
                    mike.add("DEADLINE", deadlineDescription);
                    break;
                case EVENT:
                    String eventDescription = sc.nextLine();
                    mike.add("EVENT", eventDescription);
                    break;
            }
        }
        sc.close();
    }
}