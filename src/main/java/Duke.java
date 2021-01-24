import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for Duke project
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        DukeBot dukeBot = new DukeBot();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                dukeBot.echo(sc.nextLine());
            }
            catch (DukeException exDuke){
                dukeBot.outputMessage(exDuke.getMessage());
            } catch (IOException exIO) {
                System.out.println(exIO);
            }
        }
    }
}