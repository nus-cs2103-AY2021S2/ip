import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("   Hello There! I'm Duke, always here for you!");
        System.out.println("   How can I help you today?");

        String[] arr = new String[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    System.out.println("___");
                    for (int i = 0; i < counter; i++) {
                        System.out.println("   " + (i + 1) + ". " + arr[i]);
                    }
                    System.out.println("___");
                    input = sc.next();
                    break;
                default:
                    String item = sc.next();
                    System.out.println("___\n added: read " + item + "\n___");
                    input = input.concat(" " + item);
                    arr[counter] = input;
                    counter++;
                    input = sc.next();
                    break;
            }
        }

        System.out.println("___\n Bye! Hope to see you soon! :) \n___");
    }
}
