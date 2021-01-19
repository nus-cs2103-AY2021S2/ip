import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {

    private ArrayList<String> list;
    private Duke(ArrayList<String> list){

        this.list = list;

    }

    public void greeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void add(String input){

        this.list.add(input);
        System.out.println("added: " + input);

    }

    public void display(){
        int len = this.list.size();
                for(int i = 0; i < len; i++){
                    int index = i + 1;
                    System.out.println(index + ". " + this.list.get(i));
                }
    }
    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }



    public static void main(String[] args) {

        Duke duke = new Duke(new ArrayList<>());

        duke.greeting();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                duke.bye();
                break;
            }
            else if(input.equals("list")) {
                duke.display();

            }
            else {
                duke.add(input);
            }
        }
    }
}
