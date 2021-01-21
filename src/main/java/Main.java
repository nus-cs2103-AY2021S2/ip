import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        bot.greet();
        for (int i = 0; i < 50; i++) {
            pw.print('\u2500');
        }
        pw.println();
        pw.flush();
        Parser parser = new Parser();
        int count = 0;

        while(true) {
            if(count > 0) {
                pw.println("Anything else?");
                pw.flush();
            }
            String string = br.readLine();
            try {
                parser.processInput(string, bot);
            } catch (InvalidCommandException | InvalidArgumentException ex){
                System.out.println(ex.getMessage());
                continue;
            }

            if(!(parser.isEquals("bye"))) {
                if(parser.isEquals("list")) {
                    bot.showTasks();
                } else if(parser.isEquals("done")){
                    bot.markAsDone(parser.description);
                } else if(parser.isEquals("todo")) {
                    bot.addTask(parser.description, parser.command, null);
                } else if(parser.isEquals("deadline") || parser.isEquals("event")) {
                    bot.addTask(parser.description, parser.command, parser.deadline);
                } else if(parser.isEquals("delete")) {
                    bot.removeTask(parser.description);
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