import java.util.Scanner;

/**
 * Driver class for Duke project
 */
public class Duke {
    public static void main(String[] args) {
        DukeBot dukeBot = new DukeBot();
        Scanner sc = new Scanner(System.in);

            while(true) {
                try {
                    dukeBot.echo(sc.nextLine());
                }
                catch(DukeException ex){
                    dukeBot.outputMessage(ex.getMessage());
                }
            }
        }
    }