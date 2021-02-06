import java.util.Scanner;

/**
 * Ui is a class that handles the input/output of Duke.
 */
public class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line as a string in the scanner input.
     * @return a string that is in the scanner input.
     */
    public String read() {
        return sc.nextLine();
    }

    /**
     * Checks if the next line is available. Usually used before read().
     * @return whether the scanner contains the next line.
     */
    public boolean canRead() {
        return sc.hasNextLine();
    }

    /**
     * Prints the input string to the console.
     * @param str The string to be printed to the console.
     */
    public void println(String str) {
        System.out.println(str);
    }
}
