package duke;

import java.util.ArrayList;

public class Ui {

    private static String greet = "Woof! I'm Doge Duke\n"
            + "What do you want me to do?\n"
            + "Type your request in below!";

    private static String goodbye = "Bye! Hope I was a good dog, "
            + "see you again soon!";

    static String printGreet() {
        return greet;
    }

    static String printGoodbye() {
        return goodbye;
    }

    static String printGeneralError() {
        return ("Sorry, I can't recognise this."
                + " Maybe try another command?");
    }

    static String indexList(ArrayList<Command> xs) {
        String result = "";
        for (int i = 0; i < xs.size(); i++) {
            Command value = xs.get(i);
            result += (i + 1) + ". " + value + "\n";
        }
        return result;
    }

    static String printToDo(Command command, int size) {
        return "Mlem I've added a new command for you to do:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!";
    }

    static String printDeadline(Command command, int size) {
        return "Woofers! I've added a new command with a Ded-line:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!";
    }

    static String printEvent(Command command, int size) {
        return "Much wow! I've added a new command with an Event:\n"
                + command
                + "\n"
                + "Now I can do a total of "
                + size
                + " commands!";
    }

    static String printDone(Command command) {
        return "Very Woof! "
                + "I have completed this commands:\n"
                + command
                + "\n";
    }

    static String printDelete(Command command, int size) {
        return "Noted! "
                + "This task has been removed:\n"
                + command
                + "\n"
                + "Now you have "
                + size
                + " commands remaining.";
    }

    static String printFind(ArrayList<Command> targetList, boolean isFound) {
        if (isFound) {
            return "We found some matching commands:\n"
                    + indexList(targetList);
        } else {
            return "Omo :( "
                    + "We could not find anything. "
                    + "Try another keyword?\n";
        }
    }

    static String printArchiveCompleted() {
        return "Archived all commands in text file in your data folder!";
    }

    static String printRetrievalCompleted() {
        return "Your archived commands have been added back to your current commands!";
    }
}
