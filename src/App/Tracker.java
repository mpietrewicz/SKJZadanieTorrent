package App;

import App.Comunication.Command;
import App.Comunication.Connection;
import App.Operation.Operation;
import App.Operation.TrackerOperation;

import java.io.DataOutputStream;
import java.io.IOException;
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
            System.out.println("Requested App.Comunication.Command: " +requestedCommand.getContent());
            switch (requestedCommand.getOperation()) {
                case "GET":
                    operation.get(new TrackerOperation(connection));
                    break;
                case "REGISTER":
                    // TODO: Dodać walidację komenty REGISTER
                    operation.register(new TrackerOperation(requestedCommand, connection));
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
