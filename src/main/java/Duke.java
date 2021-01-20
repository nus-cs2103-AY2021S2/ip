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
            try {
                exec.executeCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (!bot.isAlive) {
                sc.close();
                break;
            }

        }
    }
}
