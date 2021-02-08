/**
 * Doge Duke implements a virtual pet application that
 * returns different commands passed by owner (user).
 *
 * @author Chia Jia-Xi, Kymie
 * @version 1.0
 * @since 2021-01-31
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n"
                + "░█░░░░░░░░▀▄░░░░░░▄░\n"
                + "█░░▀░░▀░░░░░▀▄▄░░█░█\n"
                + "█░▄░█▀░▄░░░░░░░▀▀░░█\n"
                + "█░░▀▀▀▀░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n"
                + "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n"
                + "░░▀░░░▀░░░░░▀░░░▀░░░\n";


        System.out.println("Greetings from\n" + logo);

        String spacer = "\n____________________________________________________________\n";
        String greet = "Woof! I'm Doge Duke\n"
                + "What do you want me to do?\n"
                + "Type your request in below!\n";
        String goodbye = "Bye! Hope I was a good dog, "
                + "see you again soon!";
        String terminate = "bye";


        System.out.println(spacer + greet + spacer);

        int ctr = 0;
        ArrayList<Command> commands = new ArrayList<Command>();
        String separator = " ";

        while (true) {
            String input = sc.nextLine().trim();
            try {

                // Check for errors in errorHandling() method
                errorHandling(input);

                // To recognise terminating condition
                if (input.equals(terminate)) {
                    System.out.println(spacer + goodbye + spacer);
                    break;
                }

                // To recognise List user input
                if (input.equals("list")) {
                    System.out.println(spacer);
                    printList(commands);
                    System.out.println(spacer);

                // To recognise Done user input
                } else if (input.contains("done")) {
                    String[] doneCommand = input.split(separator);
                    int id = Integer.parseInt(doneCommand[1]) - 1;
                    Command command = commands.get(id);
                    commands.set(id, command.markDone());

                    System.out.println(spacer + "Woof! "
                            + "I have completed these commands before:\n");
                    printList(commands);
                    System.out.println(spacer);

                } else if (input.contains("delete")) {
                    String[] deleteCommand = input.split(separator);
                    int id = Integer.parseInt(deleteCommand[1]) - 1;
                    int len = commands.size();

                    System.out.println(spacer + "Noted! "
                            + "This task has been removed:\n"
                            + commands.get(id)
                            + "\n"
                            + "Now you have "
                            + (len - 1)
                            + " commands remaining.");

                    commands.remove(id);
                    System.out.println(spacer);

                } else {
                    Command command;

                    // To recognise ToDo user input
                    if (input.contains("todo")) {
                        String[] description = input.split(" ");
                        command = new ToDo(description[1]);
                        commands.add(command);
                        int len = commands.size();
                        System.out.println(spacer
                                + "Mlem I've added a new command for you to do: \n"
                                + commands.get(len-1)
                                + "\n"
                                + "Now I can do a total of "
                                + len
                                + " commands!"
                                + spacer);

                    // To recognise Event user input
                    } else if (input.contains("event")) {
                        String[] inputTime = input.split(" /at ");
                        command = new Event(inputTime[0].substring(6), inputTime[1]);
                        commands.add(command);
                        int len = commands.size();
                        System.out.println(spacer
                                + "Much wow! I've added a new command with an Event: \n"
                                + commands.get(len-1)
                                + "\n"
                                + "Now I can do a total of "
                                + len
                                + " commands!"
                                + spacer);

                    // To recognise Deadline user input
                    } else if (input.contains("deadline")) {
                        String[] inputTime = input.split(" /by ");
                        command = new Deadline(inputTime[0].substring(9), inputTime[1]);
                        commands.add(command);
                        int len = commands.size();
                        System.out.println(spacer
                                + "Woofers! I've added a new command with a Ded-line: \n"
                                + commands.get(len-1)
                                + "\n"
                                + "Now I can do a total of "
                                + len
                                + " commands!"
                                + spacer);

                    } else {}
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }
        sc.close();
    }

    /**
     * Displays Hashmap of all commands Duke is
     * trained to do
     *
     * @param xs ArrayList that retains all commands
     */
    static void printList(ArrayList<Command> xs) {
        for (int i = 0; i < xs.size(); i++) {
            Command value = xs.get(i);
            System.out.println(i + 1 + ". " + value);
        }
    }

    /**
     * Identifies different error types
     *
     * @param errorInput user inputs
     */
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

            throw new DukeException("Whoops :( Your command description cannot be empty. Try again!");

        } else if (errorInput.contains("list")
                || errorInput.contains("bye")
                || errorInput.contains("done")) {
            //Do nothing

        } else if (inputArr[1] != null) {
            //Do nothing

        // Invalid description: unknown
        } else {
            throw new DukeException("Whoops :( I'm sorry, I'm not sure what that means. "
                    + "Did you forget to add a command type?");
        }
    }
}
