public class TextException extends RuntimeException {
    private final String msgDes;

    public TextException(String message) {
        super(message);
        msgDes = message;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
