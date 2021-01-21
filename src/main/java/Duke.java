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
        System.out.println("");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int counter = 1;
                System.out.println(dash);
                System.out.println("Pai Kia Bot: i send u your task list ah");

                for (CheckList c : inputList) {
                    System.out.println(counter + c.toString());
                    counter++;
                }
                System.out.println(dash);
            } else if (input.length() > 4 && input.substring(0, 5).equals("done ")) {
                int index = -1;
                if (!input.equals("done ")) {
                    System.out.println("Pai Kia Bot: which entry u wan to check as done ah?");
                    index = Integer.parseInt(input.substring(5));
                }
                if (index >= 0 && index <= inputList.size()) {
                    inputList.set(index - 1, inputList.get(index - 1).makeDone());
                    System.out.println(dash);
                    System.out.println("Pai Kia Bot: ok i give this task a done chop: " + inputList.get(index - 1).toString().substring(1));
                    System.out.println(dash);
                }

            } else if (input.length() > 4 && input.substring(0, 5).equals("todo ")) {
                if (!input.equals("todo ")) {
                    String s = input.substring(5);
                    CheckList c = new CheckList("T", s, false);
                    inputList.add(c);
                    System.out.println("Pai Kia Bot: ok i added this task chop chop: " + c.toString().substring(1));
                }
            } else if (input.length() > 5 && input.substring(0, 6).equals("event ")) {
                if (!input.equals("event ")) {
                    String[] s = input.substring(6).split("/at ");
                    CheckList c = new CheckList("E", s[0] + "(at: " + s[1] + ")", false);
                    inputList.add(c);
                    System.out.println("Pai Kia Bot: ok i added this task chop chop: " + c.toString().substring(1));
                }
            } else if (input.length() > 8 && input.substring(0, 9).equals("deadline ")) {
                if (!input.equals("deadline ")) {
                    String[] s = input.substring(9).split("/by ");
                    CheckList c = new CheckList("D", s[0] + "(by: " + s[1] + ")", false);
                    inputList.add(c);
                    System.out.println("Pai Kia Bot: ok i added this task chop chop: " + c.toString().substring(1));
                }
            } else {
                System.out.println(dash);
                System.out.println("You: " + input);
                System.out.println("Pai Kia Bot: invalid response leh");
                System.out.println(dash);
            }

            System.out.print("Your response: ");
            input = sc.nextLine();
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
    String type;
    String content;
    boolean checked;

    CheckList(String type, String content, boolean checked) {
        this.type = type;
        this.content = content;
        this.checked = checked;
    }

    CheckList makeDone() {
        return new CheckList(this.type, this.content, true);
    }

    CheckList makeUndone() {
        return new CheckList(this.type, this.content, false);
    }

    @Override
    public String toString() {
        String typebox = "[" + this.type + "]";
        String checkbox = this.checked ? "[x]" : "[ ]";
        return "." + typebox + checkbox + " " + this.content;
    }
}