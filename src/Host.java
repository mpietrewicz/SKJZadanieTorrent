import java.io.IOException;

public class Host {
    public static void main(String[] args) throws IOException {
        int id = Integer.parseInt(args[0]);

        if(Connection.isPortValid(10000+id)) {
            System.out.println("Starting host: "+id);
        }
        else {
            System.out.println("Client id must be in range: 1-99");
            return;
        }

        Connection connection = new Connection("127.0.0.1", 10000);
        connection.sendMessage("REGISTER "+id);

        Server server = new Server(id);
        Thread serverThread = new Thread(server);
        serverThread.start();

        Client client = new Client(id);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }
}
