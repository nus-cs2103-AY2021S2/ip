public class DukeBot {
    private final String border = "\t___________________________________\n";
    private String output;

    public DukeBot() {
        greeting();
    }

    public void greeting() {
        output = border + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + border;
        System.out.println(output);
    }

    public boolean echo(String command) {
        boolean continueInput = true;

        if(command.equals("bye")) {
            output = border + "\t" + "Bye. Hope to see you again soon!\n" + border;
            continueInput = false;
        } else {
            output = border + "\t" + command + "\n" + border;
        }
        System.out.println(output);
        return continueInput;
    }
}