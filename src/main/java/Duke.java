import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static ArrayList<Task> userList = new ArrayList<>();
    public static void main(String[] args) {
        Duke.basicProgram();
    }
    private static void basicProgram(){
        final String greeting = HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE;
        final String addTaskMessage = "Got it. I've added this task";
        final String exitCommand = "bye";
        final String listCommand = "list";
        final String doneCommand = "done";
        final String addTodoCommand = "todo";
        final String addDeadlineCommand = "deadline";
        final String addEventCommand = "event";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            //task taken in
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            Task newTask;
            System.out.println(HORIZONTAL_RULE);
            switch (commandArr[0]) {
                case exitCommand:
                    System.out.println(command + ". Hope to see you again soon!\n" + HORIZONTAL_RULE);
                    sc.close();
                    isBye = true;
                    break;
                case listCommand:
                    Duke.printUserList();
                    System.out.println(HORIZONTAL_RULE);
                    break;
                case doneCommand:
                    int taskNumber = Integer.parseInt(commandArr[1]);
                    Duke.printDoneTask(taskNumber);
                    break;
                case addTodoCommand:
                    newTask = new ToDo(command.split("todo ")[1]);
                    Duke.printAddedTask(newTask);
                    userList.add(newTask);
                    break;
                case addDeadlineCommand:
                    String[] deadlineAndTask = command.split("/by ");
                    //offset of 9 to remove the "deadline " from the statement
                    newTask = new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(9));
                    Duke.printAddedTask(newTask);
                    userList.add(newTask);
                    break;
                case addEventCommand:
                    String[] eventTimeAndTask = command.split("/at");
                    //offset of 6 to remove "event " frm statement
                    newTask = new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(6));
                    Duke.printAddedTask(newTask);
                    userList.add(newTask);
                    break;
                default:
                    System.out.println("invalid command. please try again.");
            }
        }
    }
    private static void printUserList(){
        for(int i = 0; i< userList.size();i++){
            System.out.println(i+1 +". " + userList.get(i).toString());
        }
    }

    private static void printDoneTask(int taskNumber){
        Task doneTask = userList.get(taskNumber - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getTaskDetail());
        System.out.println(HORIZONTAL_RULE);
    }

    private static void printAddedTask(Task task){
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_RULE);
    }

}
