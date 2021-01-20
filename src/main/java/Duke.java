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

    public static void handleDone(String[] line) {
        if (line.length == 2) {
            String arg = line[1].replaceAll("[^0-9]", "");
            try {
                int num = Integer.parseInt(arg);
                Task.done(num);
            } catch (NumberFormatException e) {
                System.out.println("Please lah, key in number");
            }
        } else {
            Format.WRONGFORMAT();
        }
    }

    public static void handleToDo(String[] line, String message) {
        if (line.length > 1) {
            String msg = message.replaceAll(line[0], "")
                    .trim();
            Todo todo = new Todo(msg);
            System.out.println(Format.biggerBox(todo));
        } else {
            Format.WRONGFORMAT();
        }
    }
    public static void handleDeadline(String[] line, String message) {
        if (line.length > 2) {
            String[] comments = message.trim().toLowerCase().split("/");
            String msg = comments[0].replaceAll(line[0], "").trim();
            if (comments.length == 2) {
                Deadlines deadline = new Deadlines(msg, comments[1]);
                System.out.println(Format.biggerBox(deadline));
            } else {
                Format.WRONGFORMAT();
            }

        } else {
            Format.WRONGFORMAT();
        }
    }

    public static void handleEvent(String[] line, String message) {
        if (line.length > 2) {
            String[] comments = message.trim().toLowerCase().split("/");
            String msg = comments[0].replaceAll(line[0], "").trim();
            if (comments.length == 2) {
                Event event = new Event(msg, comments[1]);
                System.out.println(Format.biggerBox(event));
            } else {
                Format.WRONGFORMAT();
            }
        } else {
            Format.WRONGFORMAT();
        }
    }

    public static boolean inputHandler(String message) {
        String[] line = message.trim().toLowerCase().split(" ");
        String command = line[0];
        if (command.equals("bye")) {
            return true;
        } else {
            if (command.equals("list")) {
                Format.LISTING();
            } else if (command.equals("done")) {
                handleDone(line);
            } else if (command.equals("todo")) {
                handleToDo(line, message);
            } else if (command.equals("deadline")) {
                handleDeadline(line, message);
            } else if (command.equals("event")){
                handleEvent(line, message);
            } else {
                Task task = new Task(message);
                System.out.println(Format.chatBox("Added: " + message));
            }
            return false;
        }
    }
}
