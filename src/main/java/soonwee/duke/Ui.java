package soonwee.duke;
public class Ui {
    public String display;

    public Ui () {
        this.display = "";
    }

    public Ui (String text) {
        this.display = text;
    }

    public String printLine() {
        return "---------------------------";
    }

    public String printGreeting() {
        String text = new String();
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
        text = text + printLine() + "\n" + greet + "\n" + printLine();
        return text;
    }

    public Ui printPrompt() {
        String prompt = "We have 2 functions in this bot, namely\n";
        prompt = prompt + "1. Echo Bot\n2. Task List Bot\n";
        String instruct = "Please key in 1 or 2 to select the bot.\n";
        return new Ui(prompt + instruct);
    }


    public Ui printBye() {
        String text = "Bye. Hope to see you again soon!\n";
        text = text + printLine();
        return new Ui(text);
    }

    public Ui printTotalTasks(TaskList tasklist) {
        return new Ui("Now you have "
            + tasklist.getSize()
            + " tasks in the list.");
    }

    @Override
    public String toString() {
        return this.display;
    }
}
