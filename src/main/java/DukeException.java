public class DukeException{

    public static final void emptyBodyException() {
        System.out.println("Walao!Description empty!");
    }

    public static final void slashErrorException() {
        System.out.println("??? Too many / or no /, only 1 / allow for dates and time");
    }

    public static final void argumentErrorException() {
        System.out.println("You put so many/few ARGUMENTS for what");
    }

    public static final void missingArgumentErrorException() {
        System.out.println("Fill ur description or date lah");
    }

    public static final void commandErrorException() {
        System.out.println("I DON'T KNOW WHAT U SAYING BRO");
    }

    public static final void taskErrorException() {
        System.out.println("Walao, no such task");
    }
}
