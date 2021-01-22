package weiliang.bot;

public class BotException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private Bot bot;
    
    public Bot getBot() {
        return bot;
    }

    public BotException(Bot bot, String message) {
        super(message);
    }

}
