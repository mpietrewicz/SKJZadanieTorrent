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
            Connection connection = new Connection(connectionSocket);
            Command requestedCommand = new Command(connection.readMessage());
            System.out.println("Requested Command: " +requestedCommand.getContent());
            switch (requestedCommand.getOperation()) {
                case "GET":
                    operation.get(new TrackerOperationStrategy(connection));
                    break;
                case "REGISTER":
                    // TODO: Dodać walidację komenty REGISTER
                    operation.register(new TrackerOperationStrategy(requestedCommand, connection));
                    break;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");
                    connection.sendMessage("UNRECOGNIZED COMMAND");
                    connection.close();
                    break;
            }
        }
    }
}
