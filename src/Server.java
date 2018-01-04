import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{
    int port;
    ServerSocket serverSocket;
    Operation operation = new Operation();

    public Server(int id) throws IOException {
        this.port = 10000+id;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        System.out.println("Start Server Thread");
        while (true) {
            Socket connectionSocket = null;
            try {
                connectionSocket = serverSocket.accept();
                Connection connection = new Connection(connectionSocket);
                Command requestedCommand = new Command(connection.readMessage());
                System.out.println("Requested Command: " +requestedCommand.getContent());
                switch (requestedCommand.getOperation()) {
                    case "PULL":
                        operation.pull(new ServerOperationStrategy(connection));
                        break;
                    case "PUSH":
                        operation.push(new ServerOperationStrategy(connection));
                        break;
                    default:
                        System.out.println("UNRECOGNIZED COMMAND");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
