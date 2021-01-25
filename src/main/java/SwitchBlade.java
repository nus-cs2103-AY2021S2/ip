import java.util.Locale;
import java.util.Scanner;

public class SwitchBlade {

    private static void processCommand(String input, myList taskList) {
        String command = input.split("\\s+")[0];

        switch (command.toLowerCase(Locale.ROOT)) {
            case "list":
                System.out.println(taskList.toString());
                break;
            case "done":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.markCompleted(index - 1);
                } else {
                    System.out.println("Too many arguments, please give me just 1 task to mark as completed");
                }
                break;
            case "todo":
                addTask(input,taskList);
                break;
            case "deadline":
                addDeadline(input, taskList);
                break;
            case "event":
                addEvent(input, taskList);
                break;
            case "delete":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.delete(index - 1);
                } else {
                    System.out.println("Too many arguments, please give me just 1 task to mark as completed");
                }
                break;
            default:
                System.out.println("Unfortunately I don't know what you want me to do :L");
        }
    }

    private static void addTask(String input, myList taskList) {
        if (input.replaceAll("todo", "").length() > 0) {
            taskList.addTask(input);
        } else {
            System.out.println("It seems like you have not specified the description correctly :(");
        }
    }

    private static void addDeadline(String input, myList taskList) {
        if (input.contains("/by") && (Parser.findDeadlineDatetime(input) != null)) {
            String datetime = Parser.findDeadlineDatetime(input);
            String description = Parser.findDescription(input);
            taskList.addDeadline(description, datetime);
        } else {
            System.out.println("It seems like you have not specified the deadline correctly :(");
        }
    }

    private static void addEvent(String input, myList taskList) {
        if (input.contains("/at") && (Parser.findEventDatetime(input) != null)) {
            String[] datetimeArr = Parser.findEventDatetime(input);
            String description = Parser.findDescription(input);

            if (datetimeArr != null && datetimeArr[0] != null && datetimeArr[1] != null)
                taskList.addEvent(description, datetimeArr[0], datetimeArr[1]);
        } else {
            System.out.println("It seems like you have not specified the date and time correctly :(");
        }
    }

//    private static String findDatetime(String input, String argument) {
//        int argumentIndex = input.lastIndexOf(argument);
//        String output = input.substring(argumentIndex + argument.length());
//
//        if (output.replaceAll("\\s", "").length() < 1) {
//            System.out.println("Please enter a valid date or time");
//            return null;
//        }
//        return output;
//    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm SwitchBlade and I aim to do everything you want me to do!");
        myList taskList = new myList();

        Scanner sc = new Scanner(System.in);
        String input = "bye";

        if (sc.hasNext())
            input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            processCommand(input, taskList);

            if (sc.hasNext())
                input = sc.nextLine();
        }

        System.out.println("See you soon!");
    }
}
