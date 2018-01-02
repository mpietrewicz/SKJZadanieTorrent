import java.io.IOException;

public class PushRequest implements Runnable {
    String command;
    String port;

    public PushRequest(String command, String port) {
        this.command = command;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Connection connection = new Connection("127.0.0.1", port);
            connection.sendMessage(command);
            String response = connection.readMessage();
            System.out.println(response);
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
