import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    ArrayList<String> commandHistory = new ArrayList<>();

    public void run() {

        TaskList taskList = new TaskList();
        String[] input;

        try {
            String[] pastCommandHistory = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String command: pastCommandHistory) {
                if (command.equals("")) {
                    continue;
                }
                input = command.split(" ", 2);
                this.executeCommand(input, taskList);
            }
            sendOutput("History successfully restored!");
        } catch (InvalidPathException | IOException e) {
            sendOutput("No history found... Initializing from blank state!");
        }

        sendOutput("Hello I'm Checklst! What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().split(" ", 2);

        while (!input[0].equals("bye")) {
            this.executeCommand(input, taskList);
            
            input = scanner.nextLine().split(" ", 2);
        }

        sendOutput("Bye! Hope to see you again!");

        scanner.close();
    }

    public void executeCommand(String[] input, TaskList taskList) {
        commandHistory.add(String.join(" ", input));
        try {
            switch (input[0]) {
                case "list":
                    sendOutput(taskList.toString());
                    break;
                case "done":
                    int doneIndex = Integer.parseInt(input[1]);
                    sendOutput("Nice! I've marked this task as done!\n\t" + taskList.completeTask(doneIndex));
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(input[1]);
                    sendOutput("Alright! I've deleted this task!\n\t" + taskList.deleteTask(deleteIndex));
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
                case "save":
                    this.commandHistory.remove(this.commandHistory.size() - 1); // Remove the save command
                    try (PrintStream out = new PrintStream(new FileOutputStream("./data/checklst.txt"))) {
                        out.print(this.commandHistory.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
                    } catch (FileNotFoundException e) {
                        sendOutput(e.getMessage());
                    }
                    sendOutput("History sucessfully saved!");
                    break;
                default:
                    sendOutput("Sorry I didn't understand that command!!");
                    this.commandHistory.remove(this.commandHistory.size() - 1); // Remove the invalid commands
                    break;
            }
        } catch (ChecklstException e) {
            this.commandHistory.remove(this.commandHistory.size() - 1); // Remove the invalid commands
            sendOutput(e.getMessage());
        }
    }

}