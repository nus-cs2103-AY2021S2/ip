import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static int numOfItems = 0;
    private static String[] items = new String[100];
    private static boolean[] doneItems = new boolean[100];
    private static char[] typeOfItems = new char[100];
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greetUser();
        while(true) {
            String firstWord = sc.next();
            if(firstWord.equals("list")) listItems();
            else if(firstWord.equals("done")) doneItem();
            else if(firstWord.equals("delete")) delete();
            else if(firstWord.equals("bye")) {
                byeUser();
                break;
            }
            else addItems(firstWord);
        }
        echo();
    }

    private static void addItems(String firstWord) {
        if(firstWord.isEmpty()) {
            System.out.println("sorry come again?");
            return;
        }
        StringBuilder item = new StringBuilder(sc.nextLine());
        items[numOfItems] = item.toString();
        if(firstWord.charAt(0) == 'd' || firstWord.charAt(0) == 'D') typeOfItems[numOfItems] = 'D';
        else if(firstWord.charAt(0) == 'e' || firstWord.charAt(0) == 'E') typeOfItems[numOfItems] = 'E';
        else if(firstWord.charAt(0) == 't' || firstWord.charAt(0) == 'T') typeOfItems[numOfItems] = 'T';
        else {
            System.out.println("please specify the type of item");
            return;
        }
        numOfItems++;
        System.out.println("added: "+item);
        System.out.println("Now you have "+numOfItems+" tasks in your list");
    }

    private static void listItems() {
          System.out.println("Here are the items in your list:");
         for(int itemNo=1;itemNo<=numOfItems;itemNo++) {
             System.out.println(itemNo+"."+"["+ typeOfItems[itemNo-1]+"]"+"["+(doneItems[itemNo-1]?'X':"")+"] "+items[itemNo-1]);
         }
    }
    private static void doneItem() {
        int itemNo = -1;
        try {
            itemNo = sc.nextInt();
        }
        catch(InputMismatchException e) {
            System.out.println("enter the item number correctly");
            return;
        }
        if(itemNo > numOfItems) {
            System.out.println("oops theres an error!");
            return;
        }
        doneItems[itemNo-1] = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  "+"[X] "+ items[itemNo-1]);
    }

    private static void greetUser() {
        System.out.println("Hello, Im Duke. What do you want me to do?");
    }
    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void delete() {
        int num = sc.nextInt();
        if(num > numOfItems) {
            System.out.println("please enter the correct item id to delete");
            return;
        }
        System.out.println(" Noted. I've removed this task: ");
        System.out.print("  ");
        System.out.println("["+ typeOfItems[num-1]+"]"+"["+(doneItems[num-1]?'X':"")+"] "+items[num-1]);
        numOfItems--;
        System.out.println(" Now you have "+ numOfItems + " tasks in the list.");
        for(int currIdx=num-1;currIdx<numOfItems;currIdx++) {
            int nxtIdx = currIdx+1;
            items[currIdx] = items[nxtIdx];
            typeOfItems[currIdx] = typeOfItems[nxtIdx];
            doneItems[currIdx] = doneItems[nxtIdx];
        }
    }
    private static void echo() {
        while(true) {
            String cmd = sc.next();
            if(cmd.equals("bye")) {
                byeUser();
                break;
            }
            System.out.print(cmd+" ");
        }
    }
}
