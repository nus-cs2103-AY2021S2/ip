import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>(100);
        boolean runDuke = true;

        greet();

        while (runDuke) {
            String input = scanner.nextLine();

            switch (input) {
                case "list": StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        int numbering = i + 1;
                        builder.append(numbering);
                        builder.append(". ");
                        builder.append(tasks.get(i));

                        if (numbering != tasks.size()) {
                            builder.append("\n");
                        }
                    }
                    echo(builder.toString());
                    break;
                case "bye": echo("Bye. Hope to see you again soon!");
                    runDuke = false;
                    break;
                default:
                    tasks.add(input);
                    echo("added: " + input);
            }
        }
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _ ___ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        echo("Hello! I'm Duke.\nWhat can I do for you?");
    }

    public static void echo(String input) {
        System.out.println("==============================");
        System.out.println(input);
        System.out.println("==============================\n");
    }
}
