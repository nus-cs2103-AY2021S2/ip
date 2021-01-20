import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(Format.LOGO);
        System.out.println(Format.greeting);

        while(sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Format.farewell);
    }

    public static boolean inputHandler(String message) {
        String[] line = message.toLowerCase().split(" ");
        String command = line[0];
        if (command.equals("bye")) {
            return true;
        } else {
            if (command.equals("list")) {
                Format.LISTING();
            } else if (command.equals("done")) {
                if (line.length == 2) {
                    String arg = line[1].replaceAll("[^0-9]", "");
                    try {
                        int num = Integer.parseInt(arg);
                        Task.done(num);
                    } catch (NumberFormatException e) {
                        System.out.println("Please lah, key in number");
                    }
                } else {
                    System.out.println("Wrong format liao");
                }
            } else if (command.equals("todo")) {
                if (line.length > 1) {
                    String msg = message.replaceAll(command, "")
                            .trim();
                    Todo todo = new Todo(msg);
                    System.out.println(Format.UPPER + "Added liao: "
                            + todo.toString() + Format.SPACE
                            + "You have " + Task.getCapacity() + " tasks in the list!"
                            + Format.LOWER);
                } else {
                    System.out.println("Wrong format liao");
                }
            } else if (command.equals("deadline")) {
                if (line.length > 2) {
                    String[] comments = message.toLowerCase().split("/");
                    String msg = comments[0].replaceAll(command, "").trim();
                    if (comments.length == 2) {
                        String comment = comments[1];
                        Deadlines deadline = new Deadlines(msg, comment);
                        System.out.println(Format.UPPER + "Added liao: "
                                + deadline.toString() + Format.SPACE
                                + "You have " + Task.getCapacity() + " tasks in the list!"
                                + Format.LOWER);
                    } else {
                        System.out.println("Wrong format liao, add / at the end for date");
                    }

                } else {
                    System.out.println("Wrong format liao");
                }
            } else {
                Task task = new Task(message);
                System.out.println(Format.chatBox("Added: " + message));
            }
            return false;
        }
    }
}
