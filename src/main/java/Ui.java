public class Ui {
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hi there! I'm Duke.\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
    }

    public String readCommand(){

    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void sayBye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
