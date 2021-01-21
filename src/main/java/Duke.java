import java.util.*;
import java.lang.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "I'm Duke!\nWhat can I do for ya?\n";
        formatBox(introduction);

        Scanner sc = new Scanner(System.in);
        String[] itemArr = new String[100];
        for (int i = 0; i < 100; i++) itemArr[i] = "";
        String list;
        String input;
        int j = 0;

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye"))
                break;
            else if (input.equals("list")) {
                System.out.println("------------------------------------");
                for (int i = 0; i < itemArr.length; i++) {
                    if (itemArr[i].equals("")) break;
                    System.out.println(Integer.toString(i) + ". " + itemArr[i]);
                }
                System.out.println("------------------------------------");
            } else {
                // add to list
                itemArr[j] = input;
                j++;
                // print output
                String formattedInput = "added: ";
                formattedInput = formattedInput.concat(input);
                formatBox(formattedInput);
            }
        }
        String bye = "Bye. Hope to see you again soon!";
        formatBox(bye);
    }

    /**
     * Duke speaks in chat boxes
     * @param str input string within chat boxes
     */
    public static void formatBox(String str) {
        System.out.println("------------------------------------");
        System.out.println(str);
        System.out.println("------------------------------------");
    }
}
