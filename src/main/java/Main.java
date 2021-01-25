import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    //private final static File f = new File("src/main/data/duke.txt");
    private final static File f = new File("duke.txt");
    private final static Parser parser = new Parser();

    public static void main(String[] args) throws IOException {
        Duke bot = null;
        try {
            if (!(f.createNewFile())){
                System.out.println("hi");
                TaskList previous = FileReading.loadTask(f);
                bot = new Duke(previous);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bot == null) {
            bot = new Duke();
        }
        bot.greet();
        for (int i = 0; i < 50; i++) {
            pw.print('\u2500');
        }
        pw.println();
        pw.flush();
        int count = 0;

        while (true) {
            if (count > 0) {
                pw.println("Anything else?");
                pw.flush();
            }
            String string = br.readLine();
            try {
                parser.processInput(string, bot);
            } catch (InvalidCommandException | InvalidArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            if (!(parser.isEquals("bye"))) {
                if (parser.isEquals("list")) {
                    bot.showTasks();
                } else if (parser.isEquals("done")) {
                    bot.markAsDone(parser.description);
                    FileWriting.writeToFile(f, bot);
                } else if (parser.isEquals("todo")) {
                    bot.addTask(parser.description, parser.command, null);
                    FileWriting.writeToFile(f, bot);
                } else if (parser.isEquals("deadline") || parser.isEquals("event")) {
                    bot.addTask(parser.description, parser.command, parser.deadline);
                    FileWriting.writeToFile(f, bot);
                } else if (parser.isEquals("delete")) {
                    bot.removeTask(parser.description);
                    FileWriting.writeToFile(f, bot);
                }
            } else {
                bot.exit();
                break;
            }
            count++;
            pw.println();
            pw.flush();
        }
        pw.close();
    }
}
