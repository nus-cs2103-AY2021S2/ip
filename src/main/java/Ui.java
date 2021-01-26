import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui(){
        scanner = new Scanner(System.in);
    }

    public String getLine() throws NoSuchElementException {
        return scanner.nextLine();
    }
    public String getIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro ="Hello I'm\n" + logo +"\nWhat can I do for you?\n";

        return intro;
    }
    public String showNoMoreLinesError(){
        return "Error. No more lines detected. Exiting...";
    }
}
