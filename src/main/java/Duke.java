import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static ArrayList<Task> userList = new ArrayList<>();
    public static void main(String[] args) {
        Duke.basicProgram();
    }
    private static void basicProgram(){
        String greeting = HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE;
        String exitCommand = "bye";
        String listCommand = "list";
        String doneCommand = "done";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye){
            //task taken in
            String command = sc.nextLine();
            System.out.println(HORIZONTAL_RULE);
            if(command.equals(exitCommand)){
                System.out.println(command + ". Hope to see you again soon!\n"+ HORIZONTAL_RULE);
                sc.close();
                isBye = true;
            }
            else if(command.equals(listCommand)){
                Duke.printUserList();
                System.out.println(HORIZONTAL_RULE);
            }
            else if(command.split(" ")[0].equals(doneCommand)){
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                Duke.printDoneTask(taskNumber);
            }
            else{
                //actual task
                Task newTask = new Task(command);
                Duke.userList.add(newTask);
                System.out.println("added: " + command +"\n"+ HORIZONTAL_RULE);
            }
        }
    }
    private static void printUserList(){
        for(int i = 0; i< userList.size();i++){
            System.out.println(i+1 + ".[" + userList.get(i).getStatusIcon() + "] " + userList.get(i).getTaskDetail());
        }
    }

    private static void printDoneTask(int taskNumber){
        Task doneTask = userList.get(taskNumber - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getTaskDetail());
        System.out.println(HORIZONTAL_RULE);
    }

}
