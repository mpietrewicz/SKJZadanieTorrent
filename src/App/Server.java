package App;

import App.Comunication.Command;
import App.Comunication.Connection;
import App.Operation.Operation;
import App.Operation.ServerOperation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    int id;
    int port;
    ServerSocket serverSocket;
    Operation operation = new Operation();

    public Server(int id) throws IOException {
        this.id = id;
        this.port = 10000+id;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        System.out.println("Start App.Server Thread");
        while (true) {
            Socket connectionSocket = null;
            try {
                connectionSocket = serverSocket.accept();
                Connection connection = new Connection(connectionSocket);
                Command requestedCommand = new Command(connection.readMessage());
                System.out.println("Requested App.Comunication.Command: " +requestedCommand.getContent());
                switch (requestedCommand.getOperation()) {
                    case "PULL":
                        operation.pull(new ServerOperation(connection));
                        break;
                    case "PUSH":
                        operation.push(new ServerOperation(connection));
                        break;
                    case "GET":
                        operation.get(new ServerOperation(id, connection));
                        break;
                    default:
                        System.out.println("UNRECOGNIZED COMMAND");
                        System.out.println(requestedCommand.getContent());
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
