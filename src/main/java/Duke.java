import java.lang.reflect.Array;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dash = "____________________________________________________________";
        String greeting1 = "Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.";
        String greeting2 = "Pai Kia Bot: What you want?";
        String greeting3 = "Pai Kia Bot: I will keep a list of your responses ah";
        String farewell = "Pai Kia Bot: Leave so soon ah? Limpeh sleep first, if got no issue don't disturb me.";

        ArrayList<CheckList> inputList = new ArrayList<>();

        System.out.println(dash);
        System.out.println(greeting1);
        System.out.println(greeting2);
        System.out.println(dash);
        System.out.print("Your response: ");
        String input = sc.nextLine();
        if (!input.equals("list")) {
            inputList.add(new CheckList(input, false));
        }
        System.out.println("");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int counter = 1;
                System.out.println(dash);
                System.out.println("Pai Kia Bot: i send u your input list ah");

                for (CheckList c : inputList) {
                    System.out.println(counter + c.toString());
                    counter++;
                }
                System.out.println(dash);
            } else if (input.length() > 3 && input.substring(0,4).equals("done")) {
                int index;
                if (input.equals("done")) {
                    System.out.println("Pai Kia Bot: which entry u wan to check as done ah?");
                    index = Integer.parseInt(sc.nextLine());
                } else {
                    index = Integer.parseInt(input.substring(5));
                }
                if (index >= 0 && index <= inputList.size()) {
                    inputList.set(index - 1, inputList.get(index-1).makeDone());
                    System.out.println(dash);
                    System.out.println("ok i give this task a done chop: " + inputList.get(index-1).toString().substring(1));
                    System.out.println(dash);
                }

           } else {
                System.out.println(dash);
                System.out.println("You: " + input);
                System.out.println("Pai Kia Bot: ok can, added ur response chop chop fast game");
                System.out.println(dash);
            }

            System.out.print("Your response: ");
            input = sc.nextLine();
            if (!input.equals("list") && !(input.length() > 3 && input.substring(0,4).equals("done"))) {
                inputList.add(new CheckList(input, false));
            }
            System.out.println("");

        }

        System.out.println(dash);
        System.out.println("You: " + input);
        System.out.println(farewell);
        System.out.println(dash);
        sc.close();
    }
}

class CheckList {
    String content;
    boolean checked;

    CheckList(String content, boolean checked) {
        this.content = content;
        this.checked = checked;
    }

    CheckList makeDone() {
        return new CheckList(this.content, true);
    }

    CheckList makeUndone() {
        return new CheckList(this.content, false);
    }

    @Override
    public String toString() {
        String checkbox = this.checked ? "[x]" : "[ ]";
        return "." + checkbox + " " + this.content;
    }
}