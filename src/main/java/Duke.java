import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you? \n");
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int inputLength = input.split(" ").length;
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            if(command.equals("list")) {
                taskManager.printList();
            } else {
                try {
                    if(command.equals("delete")){
                        if (inputLength > 1) {
                            int taskId = Integer.valueOf(input.split(" ")[1]) - 1;
                            taskManager.delete(taskId);
                        } else {
                            throw new DukeException("Missing argument. Please key in the task number to delete.");
                        }
                    } else if (command.equals("done")) {
                        if (inputLength > 1) {
                            int taskId = Integer.valueOf(input.split(" ")[1]) - 1;
                            taskManager.done(taskId);
                        } else {
                            throw new DukeException("Missing argument. Please key in the task number to mark complete.");
                        }
                    } else if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                        if (inputLength > 1) {
                            String task = input.split(" ", 2)[1];
                            taskManager.add(command, task);
                        } else {
                            throw new DukeException("Missing argument. Please key in task description to add. ");
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            input = sc.nextLine();
            inputLength = input.split(" ").length;
            command = input.split(" ")[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
