package duke.command;

public class Command {

    String reply;

    public Command(String reply){
        this.reply = reply;
    }

    public void dukeReply(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + this.reply);
        System.out.println("\t____________________________________________________________");
    }

}
