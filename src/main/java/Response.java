public class Response {

    private boolean isExit;
    private String responseString;

    public Response(String responseString, boolean isExit) {
        this.responseString = responseString;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getResponseString() {
        return responseString;
    }
}
