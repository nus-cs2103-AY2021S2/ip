public class Ui{
    private final static String HELP_LIST = "";
    private final static String DIVIDER = "--------------------------------";
    public Ui(){}

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void handlePrint(String message) {
        System.out.println(message);
    }

    public void greet() {
        System.out.println("");
    }

    public void exit() {
        System.out.println("");
    }

    public void showHelp() {
        System.out.println("");
    }
}
