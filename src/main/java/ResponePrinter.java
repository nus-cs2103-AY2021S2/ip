public class ResponePrinter {

    private static final String LINESEPARATOR = "____________________________________________________________\n";

    private final String msg;

    public ResponePrinter(String msg) {
        this.msg = msg;
    }

    /**
     * The print function is used to print the respone message generated in the required format.
     */
    public void print() {
        System.out.println(LINESEPARATOR + msg + "\n" + LINESEPARATOR);
    }
}
