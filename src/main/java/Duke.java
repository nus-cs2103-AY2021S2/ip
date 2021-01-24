import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println(
        "____________________________________________________________ \n" +
        "Hello! I'm Duke \nWhat can I do for you? \n" +
        "____________________________________________________________");

        InputCommand in = new InputCommand();
        Lists programList = new Lists(FileProcess.importData());
//        System.out.println(programList.getDukeList());
        while(!in.getCommand().equals("bye")){
            in = new InputCommand(sc.nextLine());
            System.out.println(in.print(programList));
            System.out.println(FileProcess.writeData(programList.toString()));
        }
    }
}
