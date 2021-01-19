public class Main {
    public static void main(String[] args) {
        Chatbot owen = new Owen();
        Response response = owen.getResponse();
        System.out.println(response);
    }
}
