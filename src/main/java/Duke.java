import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println(
        "____________________________________________________________ \n" +
        "Hello! I'm Duke \nWhat can I do for you? \n" +
        "____________________________________________________________");

        Parser in = new Parser();
        TaskList programList = new TaskList(Storage.importData());
//        System.out.println(programList.getDukeList());
        while(!in.getCommand().equals("bye")){
            in = new Parser(sc.nextLine());
            System.out.println(in.print(programList));
            System.out.println(Storage.writeData(programList.toString()));
        }
    }
}
