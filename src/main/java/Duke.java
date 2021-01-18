public class Duke {
    public final static String  div = "\n____________________________"
        + "________________________________\n";
    public String currentMessage = "";

    public Duke() {
        this.currentMessage = "Hey there! I'm Duke\n" +
                "What can I do for you today?";
        System.out.println(this.output());
    }

    public int parse(String input) {
        switch(input) {
            case ("blah"):
                this.currentMessage = "blah";
                break;
            case ("list"):
                this.currentMessage = "list";
                break;
            case ("bye"):
                this.currentMessage = "Good bye. Hope to see you again soon!";
                return 0;
            default:
                this.currentMessage = "Not sure what you meant there. Try something else?";
        }
        return 1;
    }

    public String output() {
        return div + currentMessage + div;
    }
}
