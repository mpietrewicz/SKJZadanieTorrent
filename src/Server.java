import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                String requestedCommand = connection.readMessage();
                System.out.println("Requested Command: " +requestedCommand);

                switch (requestedCommand) {
                    case "PULL":
                        System.out.println("Mam udsotepnic plik o podanej nazwie");
                        connection.sendMessage("Udostepnilem plik!");
                        break;
                    case "PUSH":
                        System.out.println("Otrzymam plik o podanej nazwie");
                        connection.sendMessage("Odebralem plik!");
                        break;
                    default:
                        System.out.println("UNRECOGNIZED COMMAND");
                        break;
                }
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
