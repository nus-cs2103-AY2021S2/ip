/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */
public class Snom {
    public static void main(String[] args) {
        Snomio snomio = new Snomio(System.in, System.out);

        snomio.println("--------------------------------");
        snomio.println("Bonjour! I'm Snom! *squish*");
        snomio.println("Try giving me some commands, I might be able to do something!");
        snomio.println("[type 'bye' to exit program]");
        snomio.println("--------------------------------");
        snomio.flush();

        String input = snomio.readWord();
        do{
            snomio.println("--------------------------------");
            snomio.println(input);
            snomio.println("--------------------------------");
            snomio.flush();
            input = snomio.readWord();
        }while(!input.equalsIgnoreCase("bye"));

        snomio.println("--------------------------------");
        snomio.println("Bye. Hope to see you again soon!");
        snomio.println("--------------------------------");
        snomio.flush();

        snomio.close();
    }
}
