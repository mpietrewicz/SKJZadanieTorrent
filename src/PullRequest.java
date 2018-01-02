import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PullRequest implements Runnable {
    String command;
    String port;

    public PullRequest(String command, String port) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
