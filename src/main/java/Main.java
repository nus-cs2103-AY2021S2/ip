import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) throws IOException {
        Duke bot = new Duke();
        bot.greet();
        Parser parser = new Parser();

        while(true) {
            String string = br.readLine();
            try {
                parser.processInput(string, bot);
            } catch (InvalidCommandException | InvalidArgumentException ex){
                ex.printStackTrace();
                continue;
            }

            if(!(parser.isEquals("bye"))) {
                String[] starr = string.split(" ");
                if(parser.isEquals("list")) {
                    bot.list.printList();
                } else if(parser.isEquals("done")){
                    bot.list.lst.get(Integer.parseInt(starr[1]) - 1).done();
                    pw.println("Nice! I've marked this task as done:");
                    pw.printf(" %s%n", bot.list.lst.get(Integer.parseInt(starr[1]) - 1));
                    pw.flush();
                } else if(parser.isEquals("todo")) {
                    bot.addTask(parser.description, parser.command, null);
                } else if(parser.isEquals("deadline") || parser.isEquals("event")) {
                    bot.addTask(parser.description, parser.command, parser.deadline);
                }
            } else {
                bot.exit();
                break;
            }
        }
        pw.close();
    }
}