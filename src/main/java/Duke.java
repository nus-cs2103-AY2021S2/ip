import java.util.Scanner;

public class Duke {
    private final StringBuilder lengthOfChatBox = new StringBuilder();

    private void setLengthOfChatBox() {
        lengthOfChatBox.append("\n");
        lengthOfChatBox.append("-".repeat(50));
        lengthOfChatBox.append("\n");
    }

    private void formatInChatBox(String s) {
        System.out.println(lengthOfChatBox + s + lengthOfChatBox);
    }

    public void init() {
        setLengthOfChatBox();
        String greeting = "Hello I'm Mike\nWhat can I do for you?\n";
        formatInChatBox(greeting);
    }

    public boolean chat(String s) {
        if (s.toLowerCase().equals("bye")) {
            String goodbyeMessage = "Bye. Hope to see you again soon!\n";
            formatInChatBox(goodbyeMessage);
            return false;
        } else {
            formatInChatBox(s);
            return true;
        }
    }

    public static void main(String[] args) {
        Duke mike = new Duke();
        mike.init();
        Scanner sc = new Scanner(System.in);
        Boolean isChatting = true;

        while (isChatting) {
            String input = sc.next();
            isChatting = mike.chat(input);
        }
        sc.close();
    }
}
