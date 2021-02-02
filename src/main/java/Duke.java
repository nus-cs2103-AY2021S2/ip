import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String PATH = "./data/duke.txt";

        Scanner input = new Scanner(System.in);
        Storage storage = new Storage(PATH);
        TaskList taskList = new TaskList(storage.loadData());

        //Duke starts up with an introduction
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");

        while (input.hasNextLine()) {
            String command = input.nextLine();
            String[] commandArr = command.split(" ", 2);

            if (command.equals("bye")) {
                System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
                break;
            } else if (command.equals("list")) {
                taskList.printList();
            } else if ((commandArr[0]).equals("done")) {
                //If user enters an invalid task number, prompt a message
                if (Integer.parseInt(commandArr[1]) > taskList.getListSize()) {
                    System.out.println("Did you miscount your tasks? "
                            + "This task number isn't valid. Add it into my list!");
                    continue;
                }
                //If valid, change status of task and output done task
                taskList.taskDone(Integer.parseInt(commandArr[1]) - 1);
            } else if ((commandArr[0]).equals("delete")) {
                taskList.deleteTask(Integer.parseInt(commandArr[1]) - 1);
            } else {
                String taskType = commandArr[0];
                String wholeTask;
                Task item;
                switch (taskType) {
                case "todo":
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of todo cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    item = new Todo(wholeTask);
                    break;
                case "deadline": {
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of deadline cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    String[] taskArr = wholeTask.split("/");
                    item = new Deadline(taskArr[0], taskArr[1]);
                    break;
                }
                case "event": {
                    if (commandArr.length == 1) {
                        System.out.println("Oops! The description of event cannot be empty!");
                        continue;
                    }
                    wholeTask = commandArr[1];
                    String[] taskArr = wholeTask.split("/");
                    item = new Event(taskArr[0], taskArr[1]);
                    break;
                }
                default:
                    System.out.println("Oops! I don't know what this means! :(");
                    continue;
                }
                taskList.addTask(item);
            }
            storage.writeToFile(taskList.getList());
        }
    }
}

