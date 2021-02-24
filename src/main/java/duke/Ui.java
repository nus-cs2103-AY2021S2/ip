package duke;

import java.util.ArrayList;

public class Ui {

    static String spacer = "\n________________________________________________________\n";
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
            + "Type your request in below!";

    static String goodbye = "Bye! Hope I was a good dog, "
            + "see you again soon!";

//    public static String printGreeting() {
//        return (logo + spacer + greet + spacer);
//    }

    static String printGreet() { return (spacer + greet + spacer); }
    static String printGoodbye() {
        return (spacer + goodbye + spacer);
    }

    static String printGeneralError() {
        return (spacer
                + "Sorry, I can't recognise this."
                + " Maybe try another command?"
                + spacer);
    }

    static String indexList(ArrayList<Command> xs) {
        String result = "";
        for (int i = 0; i < xs.size(); i++) {
            Command value = xs.get(i);
            result += (i + 1) + ". " + value +"\n";
        }
        return result;
    }

    static String printToDo(Command command, int size) {
        return (spacer
                + "Mlem I've added a new command for you to do:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

    static String printDeadline(Command command, int size) {
        return (spacer
                + "Woofers! I've added a new command with a Ded-line:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

    static String printEvent(Command command, int size) {
        return (spacer
                + "Much wow! I've added a new command with an Event:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!"
                + spacer);
    }

     static String printDone(Command command) {
        return (spacer
                + "Very Woof! "
                + "I have completed this commands:\n"
                + command
                + "\n"
                + spacer);
    }

    static String printDelete(Command command, int size) {
        return (spacer
                + "Noted! "
                + "This task has been removed:\n"
                + command
                + "\n"
                + "Now you have "
                + size
                + " commands remaining."
                + spacer);
    }

    static String printFind(ArrayList<Command> targetList, boolean isFound) {
        if (isFound) {
            return (spacer
                    + "We found some matching commands:\n"
                    + indexList(targetList)
                    + spacer);
        } else {
            return (spacer
                    + "Omo :( "
                    + "We could not find anything. "
                    + "Try another keyword?\n"
                    + spacer);
        }
    }

}
