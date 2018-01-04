import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Tracker {
    public static void main(String[] args) throws IOException {
        int port = 10000;
        ServerSocket serverSocket = new ServerSocket(port);
        DataOutputStream outputStream;
        Operation operation = new Operation();

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            outputStream = new DataOutputStream(connectionSocket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(connectionSocket.getInputStream());
            BufferedReader inputStreamBufferedReader = new BufferedReader(inputStream);

            Command command = new Command(inputStreamBufferedReader.readLine());
            switch (command.getOperation()) {
                case "GET":
                    operation.get(new TrackerOperationStrategy(outputStream));
                    break;
                case "SET":
                    operation.set(new TrackerOperationStrategy(outputStream));
                    break;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");
                    outputStream.writeBytes("UNRECOGNIZED COMMAND" +'\n');
                    break;
            }
        }
    }
}
