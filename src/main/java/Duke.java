import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        DukeBot bot = new DukeBot("Duke");
        Scanner sc = new Scanner(System.in);
        System.out.println(bot);
        String command;
        Execute exec = new Execute(bot);

        while (sc.hasNext()) {
            command = sc.nextLine();
            exec.executeCommand(command);
            if (!bot.isAlive) {
                sc.close();
                break;
            }

        }
    }
}
