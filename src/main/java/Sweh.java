import java.util.Optional;
import java.util.Scanner;

public class Sweh {
    public static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        String logo = " _____  _    _ _____ _   _ \n" +
            "/  ___|| |  | |  ___| | | | \n" +
            "\\ `--. | |  | | |__ | |_| | \n" +
            " `--. \\| |/\\| |  __||  _  | \n" +
            "/\\__/ /\\  /\\  / |___| | | | \n" +
            "\\____/  \\/  \\/\\____/\\_| |_/ \n";

        String greeting = "Hello, I am\n" 
            + logo 
            + "\nYour Simple Word-Executed Helper!"
            + "\nWhat shall we do today?\n";
                                  
        System.out.println(greeting);
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            System.out.println(Formatter.formatOut(respond(cmd)));
            if (cmd.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    public static String respond(String commandString) {
        String[] commandArr = commandString.split(" ", 2);
        String command = commandArr[0];
        Optional<String> argOptional = commandArr.length > 1
            ? Optional.of(commandArr[1])
            : Optional.empty();

        switch (command) {
            case "list":
                return taskList.listTasks();
            case "done":
                String args = argOptional.get();
                return taskList.markTaskDone(Integer.parseInt(args) - 1);
            case "bye":
                return "Bye. See ya again soon!";
            default:
                return taskList.addTask(command);
        }
    }

    public static String echo(String input) {
        return input;
    }
}
