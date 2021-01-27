package Ui;

public class Ui {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";

    Ui() {
        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);

    }

//    String readCommand() {
//        //receive from scanner?
//    }
//

    void showLine() {
        System.out.println(line);
    }

    String line() {
        return line;
    }

    void showLoadingError() {
        //"No prior saved data!"
        System.out.println(line + "Kobe detected no prior saved data!\n" + line);
    }
}