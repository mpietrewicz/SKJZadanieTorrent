package App.Operation;

import App.Comunication.Command;
import App.Comunication.Connection;

import java.io.IOException;

public class ClientOperation implements OperationStrategy {
    Command command;
    String port;

    public ClientOperation(Command command, String port) {
        this.command = command;
        this.port = port;
    }

    @Override
    public void get() throws IOException {
        System.out.println("App.Operation.ClientOperation");
        defaultOperation();
    }

    @Override
    public void register() throws IOException {
        System.out.println("App.Operation.ClientOperation");
        defaultOperation();
    }

    @Override
    public void push() throws IOException {
        System.out.println("App.Operation.ClientOperation");
        defaultOperation();
    }

    @Override
    public void pull() throws IOException {
        System.out.println("App.Operation.ClientOperation");
        defaultOperation();
    }

    public void defaultOperation() throws IOException {
        DefaultOperationThread defaultOperationThread = new DefaultOperationThread();
    }

    class DefaultOperationThread {
        private Thread thread;

        public DefaultOperationThread() {
            thread = new Thread() {
                public void run() {
                    try {
                        Connection connection = new Connection("127.0.0.1", port);
                        connection.sendMessage(command.getContent());
                        String response = connection.readMessage();
                        System.out.println(response);
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
}
