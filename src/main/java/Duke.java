import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hey there! This is Kums");
        System.out.println("How can i help?");
        System.out.println("---------------------------------------");
        String[] list = new String[100];
        int listLength = 0;
        String command = br.readLine();
        System.out.println("---------------------------------------");
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                list[listLength] = (listLength + 1) + ". " + command;
                listLength++;
                System.out.println("added: " + command);
            } else {
                for (int i = 0;  i < listLength; i++) {
                    System.out.println(list[i]);
                }
            }
            System.out.println("---------------------------------------");
            command = br.readLine();
            System.out.println("---------------------------------------");
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------");
    }
}
