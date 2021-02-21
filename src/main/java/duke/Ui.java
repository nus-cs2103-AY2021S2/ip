package duke;

import java.util.ArrayList;

public class Ui {

    static String spacer = "\n____________________________________________________________\n";
    static String logo = "Greetings from\n"
            + "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n"
            + "░█░░░░░░░░▀▄░░░░░░▄░\n"
            + "█░░▀░░▀░░░░░▀▄▄░░█░█\n"
            + "█░▄░█▀░▄░░░░░░░▀▀░░█\n"
            + "█░░▀▀▀▀░░░░░░░░░░░░█\n"
            + "█░░░░░░░░░░░░░░░░░░█\n"
            + "█░░░░░░░░░░░░░░░░░░█\n"
            + "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n"
            + "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n"
            + "░░▀░░░▀░░░░░▀░░░▀░░░\n";
    static String greet = "Woof! I'm Doge Duke\n"
            + "What do you want me to do?\n"
            + "Type your request in below!\n";
    static String goodbye = "Bye! Hope I was a good dog, "
            + "see you again soon!";

    static void printGreeting() {
        System.out.println(spacer + greet + spacer);
    }

    static void printGoodbye() {
        System.out.println(spacer + goodbye + spacer);
    }

    static void printToDo(Command command, int size) {
        System.out.println(spacer
                + "Mlem I've added a new command for you to do:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

    static void printDeadline(Command command, int size) {
        System.out.println(spacer
                + "Woofers! I've added a new command with a Ded-line:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

    static void printEvent(Command command, int size) {
        System.out.println(spacer
                + "Much wow! I've added a new command with an Event:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

    static void printDone(ArrayList<Command> xs) {
        System.out.println(spacer
                + "Very Woof! "
                + "I have completed these commands before:");
        duke.CommandList.printList();
        System.out.println(spacer);
    }

    static void printDelete(Command command, int size) {
        System.out.println(spacer
                + "Noted! "
                + "This task has been removed:\n"
                + command
                + "\n"
                + "Now you have "
                + size
                + " commands remaining."
                + spacer);
    }

}
