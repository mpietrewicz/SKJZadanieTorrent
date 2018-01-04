import java.io.IOException;

public class ClientOperationStrategy implements OperationStrategy {
    Command command;
    String port;

    public ClientOperationStrategy(Command command, String port) {
        this.command = command;
        this.port = port;
    }

    @Override
    public void get() throws IOException {
        System.out.println("ClientOperationStrategy");
        defaultOperation();
    }

    @Override
    public void set() throws IOException {
        System.out.println("ClientOperationStrategy");
        defaultOperation();
    }

    @Override
    public void push() throws IOException {
        System.out.println("ClientOperationStrategy");
        defaultOperation();
    }

    @Override
    public void pull() throws IOException {
        System.out.println("ClientOperationStrategy");
        defaultOperation();
    }

    public void defaultOperation() throws IOException {
        Connection connection = new Connection("127.0.0.1", port);
        connection.sendMessage(command.getContent());
        String response = connection.readMessage();
        System.out.println(response);
        connection.close();
    }
}
