import java.util.Scanner;

public class Duke {
    private static final String BORDER = "-------------------------------------";

    public static void main(String[] args) {
        Duke.printWithBorders("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        String[] records = new String[100];
        int numberOfRecords = 0;

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                Duke.printWithBorders("Bye. Hope to see you soon!");
                return;
            } else if (userInput.equals("list")) {
                String output = "";
                for (int i = 0; i < numberOfRecords; i++) {
                    output += (i + 1) + ". " + records[i];
                    if (i != numberOfRecords - 1) {
                        output += "\n";
                    }
                }
                Duke.printWithBorders(output);
            } else {
                records[numberOfRecords++] = userInput;
                String output = "added: " + userInput;
                Duke.printWithBorders(output);
            }
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
