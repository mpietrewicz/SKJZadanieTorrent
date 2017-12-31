import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Host {
    public static void main(String[] args) throws IOException {
        int id = Integer.parseInt(args[0]);
        Socket clientSocket;
        Connection connection;
        String response;
        String port;

        if(Connection.isPortValid(10000+id)) {
            System.out.println("Starting host: "+id);
        }
        else {
            System.out.println("Host id must be in range: 1-99");
            return;
        }

        Server server = new Server(id);
        Thread serverThread = new Thread(server);
        serverThread.start();

        BufferedReader userCommand = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String command = userCommand.readLine();

            switch (command) {
                case "GET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");

                    connection = new Connection("127.0.0.1", 10000);
                    connection.sendMessage(command);
                    response = connection.readMessage();
                    System.out.println(response);
//                    clientSocket.close();
                    break;
                case "SET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy udostepnic listę udostępnianych plików");
                    connection = new Connection("127.0.0.1", 10000);
                    connection.sendMessage(command);
                    response = connection.readMessage();
                    System.out.println(response);
//                    clientSocket.close();
                    break;
                case "PULL":
                    System.out.println("ściągamy z wybranego hosta plik o zadanej nazwie ");
                    System.out.print("Podaj nazwe hosta z ktorego ma być pobrany plik: ");
                    port = userCommand.readLine();
                    System.out.println(command);
                    connection = new Connection("127.0.0.1", port);
                    connection.sendMessage(command);
                    response = connection.readMessage();
                    System.out.println(response);
                    break;
                case "PUSH":
                    System.out.println("wrzucamy na wybrany host pliku o zadanej nazwie");
                    System.out.print("Podaj nazwe hosta do ktorego ma być wysłany plik: ");
                    port = userCommand.readLine();
                    System.out.println(command);
                    connection = new Connection("127.0.0.1", port);
                    connection.sendMessage(command);
                    response = connection.readMessage();
                    System.out.println(response);
                    break;
                case "QUIT":
                    return;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");;
                    break;
            }
        }
    }
}
