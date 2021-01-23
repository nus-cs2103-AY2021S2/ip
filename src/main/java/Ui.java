public class Ui {
    static String horizontalLine = "\t--------------------------------\n";

    public void printFormatMessage(String str) {
        System.out.print(Ui.horizontalLine);
        System.out.println(str);
        System.out.print(Ui.horizontalLine);
    }

    public void welcome() {
        printFormatMessage("\t  Hello! I'm Duke\n" + "\t  What can I do for you?");
    }

    public void welfare() {
        printFormatMessage("\t  Bye. Hope to see you again soon!");
    }


}
