package soonwee.duke;

/**
 * Represents a Ui instance. A Ui instance will display the information
 * shown at any point of time.
 */
public class Ui {

    public String display;

    /**
     * Instantiate an empty Ui display.
     */
    public Ui () {
        this.display = "";
    }

    /**
     * Instantiate a Ui display with some text.
     *
     * @param text some text.
     */
    public Ui (String text) {
        this.display = text;
    }

    /**
     * Prints a number of lines.
     *
     * @return lines.
     */
    public String printLine() {
        return "---------------------------";
    }

    /**
     * Prints greetings.
     *
     * @return greetings.
     */
    public String printGreeting() {
        String text = new String();
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
        text = text + printLine() + "\n" + greet + "\n" + printLine();
        return text;
    }

    /**
     * Prints prompt to direct users.
     *
     * @return new Ui display with its prompt.
     */
    public Ui printPrompt() {
        String prompt = "We have 2 functions in this bot, namely\n";
        prompt = prompt + "1. Echo Bot\n2. Task List Bot\n";
        String instruct = "Please key in 1 or 2 to select the bot.\n";
        return new Ui(prompt + instruct);
    }


    /**
     * Prints bye.
     *
     * @return bye.
     */
    public Ui printBye() {
        String text = "Bye. Hope to see you again soon!\n";
        text = text + printLine();
        return new Ui(text);
    }

    /**
     * Prints current total tasks.
     *
     * @param tasklist list of tasks.
     * @return a display indicating the number of tasks.
     */
    public Ui printTotalTasks(TaskList tasklist) {
        return new Ui("Now you have " + tasklist.getSize() + " tasks in the list.");
    }

    @Override
    public String toString() {
        return this.display;
    }
}
