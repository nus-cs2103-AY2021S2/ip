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
        String[] input = scanner.nextLine().split(" ", 2);
        TaskList taskList = new TaskList();

        while (!input[0].equals("bye")) {

            try {
                switch (input[0]) {
                    case "list":
                        sendOutput(taskList.toString());
                        break;
                    case "done":
                        int index = Integer.parseInt(input[1]);
                        sendOutput("Nice! I've marked this item as done\n\t" + taskList.completeTask(index));
                        break;
                    case "todo":
                        Task newTodo = Todo.makeTodo(input[1]);
                        sendOutput(taskList.add(newTodo));
                        break;
                    case "event":
                        Task newEvent = Event.makeEvent(input[1]);
                        sendOutput(taskList.add(newEvent));
                        break;
                    case "deadline":
                        Task newDeadline = Deadline.makeDeadline(input[1]);
                        sendOutput(taskList.add(newDeadline));
                        break;
                    default:
                        sendOutput("Sorry I didn't understand that command!!");
                        break;
                }
            } catch (ChecklstException e) {
                sendOutput(e.getMessage());
            }
            


            input = scanner.nextLine().split(" ", 2);
        }

        sendOutput("Bye! Hope to see you again!");

        scanner.close();
    }


}
