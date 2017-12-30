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
                // TODO Add object Connection as Thread
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
