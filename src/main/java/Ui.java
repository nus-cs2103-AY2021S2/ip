public class Ui {

    public Ui(){ }

    public String showResultToUser(CommandResult result) {
        return result.getFeedbackToUser();
    }

    public String showGoodbyeMessage(){
        return "Bye. Hope to see you again soon!";
    }

    public String showLoadingError(){
        return "OOPS!! Duke fails to load, please restart!";
    }

    public String showErrorMessage(String message){
        return message;
    }
}
