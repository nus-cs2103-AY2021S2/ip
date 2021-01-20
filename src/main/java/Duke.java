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
                Todo todo = new Todo(message);
                System.out.println(Format.chatBox("Added liao: " + todo.toString()));
            } else {
                Task task = new Task(message);
                System.out.println(Format.chatBox("Added: " + message));
            }
            return false;
        }
    }
}
