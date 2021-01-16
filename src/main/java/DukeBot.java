public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private String output;

    public DukeBot() {
        greeting();
    }

    public void greeting() {
        output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
        System.out.println(output);
    }

    public boolean echo(String command) {
        boolean continueInput = true;

        if(command.equals("bye")) {
            output = BORDER + "\t" + "Bye. Hope to see you again soon!\n" + BORDER;
            continueInput = false;
        } else {
            output = BORDER + "\t" + command + "\n" + BORDER;
        }
        System.out.println(output);
        return continueInput;
    }
}