package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Parser {
    CommandList commandList;
    static String terminate = "bye";
    private final static String separator = " ";
    private final static String regexToDo = " ";
    private final static String regexDeadline = " /by ";
    private final static String regexEvent = " /at ";

    public Parser(CommandList commandList) {
        this.commandList = commandList;
    }

    static void errorHandling(String errorInput) throws DukeException {
        String[] inputArr = new String[100];
        if (errorInput.contains(" ")) {
            inputArr = errorInput.split(" ");
        } else {
            inputArr[0] = errorInput;
        }

        // Empty description
        if ((errorInput.contains("todo")
                || errorInput.contains("deadline")
                || errorInput.contains("event"))
                && inputArr[1] == null) {

            throw new DukeException("Eh? Your command description cannot be empty. Try again!");

        } else if (errorInput.contains("list")
                || errorInput.contains("bye")) {
            //Do nothing

        } else if ((errorInput.contains("done")
                || errorInput.contains("delete"))
                && inputArr[1] == null) {

            throw new DukeException("What are you referring to? Remember to key in the correct command id!");

        } else if (inputArr[1] != null) {
            //Do nothing

        // Invalid description: unknown
        } else {
            throw new DukeException("Whoops :( I'm sorry, I'm not sure what that means. "
                    + "Did you forget to add a command type?");
        }
    }

    void parseAdd(String input) {
        Command command;
        try {
            if (input.contains("todo")) {
                String[] description = input.split(regexToDo);
                command = new ToDo(description[1]);
                commandList.addCommand(command, "T");
            } else if (input.contains("deadline")) {
                String[] inputTime = input.split(regexDeadline);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Deadline(inputTime[0].substring(9), parseDate);
                commandList.addCommand(command, "D");

            } else if (input.contains("event")) {
                String[] inputTime = input.split(regexEvent);
                LocalDate parseDate = LocalDate.parse(inputTime[1].trim());
                command = new Event(inputTime[0].substring(6), parseDate);
                commandList.addCommand(command, "E");

            } else {

            }
        } catch (DateTimeParseException e) {
            System.out.println("This date doesnt exist! "
                    + "The right format should be in yyyy-mm-dd.");
        }
    }

     void parseDelete(String input) {
        String[] deleteCommand = input.split(separator);
        int id = Integer.parseInt(deleteCommand[1]) - 1;
        commandList.deleteCommand(id);
    }

     void parseList() {
       System.out.println(Ui.spacer);
       commandList.printList();
       System.out.println(Ui.spacer);
   }

    void parseDone(String input) {
       String[] doneCommand = input.split(separator);
       int id = Integer.parseInt(doneCommand[1]) - 1;
       commandList.doneCommand(id);
   }

    public void parseAll() {
        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine().trim();

            try {
                errorHandling(input);

                if (input.equals(terminate)) {
                    Ui.printGoodbye();
                    break;
                } else if (input.equals("list")) {
                    parseList();
                } else if (input.contains("done")) {
                    parseDone(input);
                } else if (input.contains("delete")) {
                    parseDelete(input);
                } else if (input.contains("todo")
                        || input.contains("deadline")
                        || input.contains("event")) {
                    parseAdd(input);
                } else {
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        sc.close();
   }
}
