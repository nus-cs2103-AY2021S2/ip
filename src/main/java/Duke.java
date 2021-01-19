import java.util.Scanner;

public class Duke {
    public static int inputHandler(String s) {
        String temp = s.toLowerCase();
        if (s.equals("bye")) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(Format.LOGO);
        System.out.println(Format.greeting);

        while(sc.hasNext()) {
            String message = sc.nextLine();
            Task t = new Task(message);
            int type = inputHandler(message);
            if (type == 0) {
                break;
            } else {
                System.out.println(Format.chatBox("Added: " + message));
            }

        }
        
        System.out.println(Format.farewell);
    }
}
