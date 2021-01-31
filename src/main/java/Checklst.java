import java.util.Scanner;

public class Checklst {
    
    public static void main(String[] args) {
        Checklst checklst = new Checklst();
        checklst.run();
    }

    public static void sendOutput(String output) {
        System.out.println("\t----------------------------------------");
        System.out.println("\t" + output);
        System.out.println("\t----------------------------------------");
    }

    public void run() {
        sendOutput("Hello I'm Checklst! What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        TaskList taskList = new TaskList();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                sendOutput(taskList.toString());
            } else if (input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                Task newTask = taskList.get(index).complete();
                taskList.replace(index, newTask);
                sendOutput("Nice! I've marked this item as done\n\t" + newTask);
            } else {
                Task task = new Task(input, false);
                taskList.add(task);
                sendOutput("Added: " + task);
            }

            input = scanner.nextLine();
        }

        sendOutput("Bye! Hope to see you again!");

        scanner.close();
    }


}
