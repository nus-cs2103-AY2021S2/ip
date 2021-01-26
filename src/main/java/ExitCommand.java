public class ExitCommand extends Command{

    boolean isExit;

    public ExitCommand(){
        this.isExit = true;
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

}
