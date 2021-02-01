package kobe;

public class Ui {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";

    /**
     * Constructor for the Ui object.
     */
    Ui() {
        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);

    }

    /**
     * To print a line with indentation.
     */
    void showLine() {
        System.out.println(line);
    }

    /**
     * To print a line with indentation.
     *
     * @return  a line with indentation
     */
    String line() {
        return line;
    }

    /**
     * To display an error message in the context of Kobe
     */
    void showLoadingError() {
        //"No prior saved data!"
        System.out.println(line + "Kobe detected no prior saved data!\n" + line);
    }
}