import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Duke {
    private static List<String> tasks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String logo = "           ___  _           ,_,\n"
                + "         |  _  | |   ___   / /\n"
                + "         | | | | |  / _ \\ =; ;=,\n"
                + "         | |_| | |_| |_| \\ | |\n"
                + "          \\___/\\___/---;_| |_|\n";

        String border = " --*---*---*---*---*---*---*---*---*---*--";
        System.out.println(logo + "\n" + border + "\n  Hey there, Olaf here!\n  What can I do for you?");

        while(true) {
            String command = bf.readLine();
            if(command.toLowerCase().equals("bye")){
                String out = "Aww hope to see you soon, goodbye!";
                System.out.println(border + "\n  " + out + "\n" + border);
                break;
            } else if(command.toLowerCase().equals("list")){
                System.out.println(border);
                // adapted from: https://howtodoinjava.com/java/collections/arraylist/iterate-through-objects/
                int idx = 0;
                while (tasks.size() > idx){
                    System.out.printf("  %s. %s\n",String.valueOf(idx+1),tasks.get(idx++));
                }
                System.out.println(border);
            } else {
                tasks.add(command);
                System.out.println(border + "\n  added: " + command + "\n" + border);
            }
        }
    }
}
