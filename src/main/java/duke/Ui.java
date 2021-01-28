package duke;

import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showUsage() {
        this.say("Hey! These are the commands available:");
        this.say("\t- usage");
        this.say("\t- list");
        this.say("\t- todo <task_description>");
        this.say("\t- deadline <task_description> /by <date_time>");
        this.say("\t- event <task_description> /at <date_time>");
        this.say("\t- done <task_number>");
        this.say("\t- delete <task_number>");
        this.say("\t- save");
        this.say("\t- bye");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGreetings() {
        this.showLine();
        System.out.println(" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|");
        this.showLine();
        this.say("Hey, hello there! I'm Duke, your personal chat bot.");
        this.say("To know more about what I can do, type 'usage'.");
        this.say("So... Is there anything I can do for you today?");
    }

    public boolean toExit() {
        this.say("Are you sure? (Y/N)");
        String confirmation = this.ask();
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            this.say("Alright, take care. I hope to see you again soon!");
            return true;
        }
        this.say("Hmm... alright I'll stay.");
        return false;
    }

    public void showLoadingError() {
        this.say("Unable to load file.");
    }

    public void showError(String message) {
        this.say(message);
    }

    public void say(String message, Boolean newLine) {
        System.out.print(">> " + message);
        if (newLine)
            System.out.print("\n");
    }

    public void say(String message) {
        this.say(message, true);
    }

    public String ask() {
        System.out.print("<< ");
        return this.sc.nextLine();
    }
}
