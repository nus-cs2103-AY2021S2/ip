import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException.NoDescriptionException {
        Scanner sc= new Scanner(System.in);
        System.out.println(
        "____________________________________________________________ \n" +
        "Hello! I'm Duke \nWhat can I do for you? \n" +
        "____________________________________________________________");

        inputCommand in = new inputCommand();
        lists programList = new lists();
        while(!in.getCommand().equals("bye")){
            in = new inputCommand(sc.nextLine());
            System.out.println(in.print(programList));
        }

    }
}
