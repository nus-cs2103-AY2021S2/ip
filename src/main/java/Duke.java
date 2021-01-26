import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Duke {
    private Storage storage;

    public static void main(String[] args) throws DukeException, IOException {
        TaskList list = new TaskList();
        Storage storage = new Storage("./myData.txt");
        storage.initialise(list);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("You currently have " + list.getNumItems() + " tasks.");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            Parser.parse(list, str);
            str = sc.nextLine();
        }
        storage.finalise(list);
        System.out.println("Bye friend, see you soon!");
    }
}
