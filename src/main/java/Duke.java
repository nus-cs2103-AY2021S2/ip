import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static final String rightIndent = "  ";
    private static final String textSpacer = rightIndent + "|" + " ";
    private static final String responseBoxTop = rightIndent + "_________________________________________\n";
    private static final String responseBoxBottom = rightIndent + "|______________________________________\n"
            + rightIndent + "                                        \\|\n";
    private static Scanner sc = new Scanner(System.in);
    private static boolean exit = false;

    public static void main(String[] args) {
        displayIntro();
        while (exit == false && sc.hasNext()) {
            processInput(sc.nextLine());
        }
        sc.close();
    }

    private static void processInput(String userInput) {
        String userInput_UC = userInput.toUpperCase();
        Queries query = Queries.ECHO;

        if (Queries.containsValue(userInput_UC)) {
            query = Queries.valueOf(userInput_UC);
        }

        switch (query) {
            case BYE:
                String response = "Bye. Hope to see you again soon!";
                respond(response);
                exit = true;
                break;
            default:
                respond(userInput);
        }
    }

    private static void displayIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String response = "Hello from\n"
                + logo + "\n"
                + "What can I do for you?\n";
        respond(response);
    }

    private static void respond(String response) {
        String indentedResponse = Arrays.stream(response.split("\n"))
                .map(line -> textSpacer + line)
                .collect(Collectors.joining("\n")) + "\n";
        String output = responseBoxTop
                + indentedResponse
                + responseBoxBottom;

        System.out.println(output);
    }

}

