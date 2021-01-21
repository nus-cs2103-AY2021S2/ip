import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     Konichiwa~~ Watashi wa \n" + logo + " desu!\n     What can I do for you today?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while(!command.equals("bye")){
            System.out.println("     Hai, "+ command);
            command = sc.nextLine();
        }
        System.out.println("     Sayonara! Mata ne~ ;)");
    }
}
