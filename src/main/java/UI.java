import java.util.Scanner;

public class UI {
    Scanner io;

    UI(){
        io = new Scanner(System.in);
    }

    public String getCommand(){
        return io.nextLine();
    }

    public void greetings(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello this is\n" + logo + "\nHow may I help you?");
    }

    public void goodbye(){
        System.out.println("Goodbye for now.\nHope to see you soon!");
    }
}
