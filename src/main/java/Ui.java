public class Ui {
    String logo =
              " ____        _        \n" //TODO: Figure out if this is allowed by style
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String separator = "------------------\n";

    public void startUpMessage(){
        System.out.println("Hello from\n" + logo);
        System.out.println("No unicode allowed");
    }
    public void goodByeMessage(){
        System.out.println(separator + "Goodbye from\n" + logo);
    }
}
