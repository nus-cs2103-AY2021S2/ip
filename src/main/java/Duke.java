import java.util.Scanner;

public class Duke {
    public static int inputHandler(String s) {
        String temp = s.toLowerCase();
        if (s.equals("bye")) {
            return 0;
        } else if (s.equals("list")) {
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(Format.LOGO);
        System.out.println(Format.greeting);

        while(sc.hasNext()) {
            String message = sc.nextLine();

            int type = inputHandler(message);
            if (type == 0) {
                break;
            } else if (type == 1) {
                Format.LISTING();
            }else {
                Task t = new Task(message);
                System.out.println(Format.chatBox("Added: " + message));
            }

        }
        
        System.out.println(Format.farewell);
    }
}
