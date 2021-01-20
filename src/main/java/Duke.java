import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final StringBuilder lengthOfChatBox = new StringBuilder();
    private final ArrayList<String> textList = new ArrayList<>();

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

    public void list() {
        int numOfText = textList.size();
        StringBuilder output = new StringBuilder();

        if (textList.isEmpty()) {
            output.append("No text saved\n");
        } else {
            int n = 1;
            for (int i = 0; i < textList.size(); i++) {
                output.append(i + 1).append(". ").append(textList.get(i)).append("\n");
            }
        }
        formatInChatBox(output.toString());
    }

    public void add(String s) {
        textList.add(s);
        String output = "Added: " + s;
        formatInChatBox(output);
    }

    public boolean chat(String s, Duke d) {
        if (s.toLowerCase().equals("bye")) {
            String goodbyeMessage = "Bye. Hope to see you again soon!\n";
            formatInChatBox(goodbyeMessage);
            return false;
        } else if (s.toLowerCase().equals("list")) {
            d.list();
        } else {
            d.add(s);
        }
        return true;
    }

    public static void main(String[] args) {
        Duke mike = new Duke();
        mike.init();
        Scanner sc = new Scanner(System.in);
        Boolean isChatting = true;

        while (isChatting) {
            String input = sc.nextLine();
            isChatting = mike.chat(input, mike);
        }
        sc.close();
    }
}
