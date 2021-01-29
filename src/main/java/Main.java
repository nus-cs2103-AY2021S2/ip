import blarb.Blarb;

/**
 * Main Class of Execution for IP.
 */
public class Main {
    /**
     * Main method for execution.
     *
     * @param args Relative filepath to storage file. (Optional)
     */
    public static void main(String[] args) {
        Blarb blarb;
        if (args.length > 0) {
            blarb = new Blarb(args[0]);
        } else {
            blarb = new Blarb();
        }
        blarb.run();
    }
}
