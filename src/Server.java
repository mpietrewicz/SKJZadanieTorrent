import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{
    int port;
    ServerSocket serverSocket;

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
                connection.setCommand(requestedCommand.getContent());
                switch (requestedCommand.getOperation()) {
                    case "PULL":
                        Thread pullResponseThread = new Thread(new PullResponse(connection, requestedCommand));
                        pullResponseThread.start();
                        break;
                    case "PUSH":
                        Thread pushResponseThread = new Thread(new PushResponse(connection, requestedCommand));
                        pushResponseThread.start();
                        break;
                    default:
                        System.out.println("UNRECOGNIZED COMMAND");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
