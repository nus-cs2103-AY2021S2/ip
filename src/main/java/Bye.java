public class Bye implements Command {

    private boolean isExitCommand = true;


    public Bye() {
    }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    @Override
    public String runCommand() {
       return "Bye. Hope to see you again soon!";
    }
}
