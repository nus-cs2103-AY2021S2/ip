public class DukeException {
    //error for todo
    public static class TodoEmpty extends BaseException{
        public TodoEmpty(String message){
            super(message);
        }
    }
    //error for Invalid command
    public static class InvalidCommand extends BaseException{
        public InvalidCommand(String message){
            super(message);
        }
    }


}
