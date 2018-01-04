import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
            outputStream = new DataOutputStream(connectionSocket.getOutputStream());
            InputStreamReader inputStream = new InputStreamReader(connectionSocket.getInputStream());
            BufferedReader inputStreamBufferedReader = new BufferedReader(inputStream);

            Command command = new Command(inputStreamBufferedReader.readLine());
            switch (command.getOperation()) {
                case "GET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");
                    outputStream.writeBytes("FILES: []" +'\n'); // TODO Zwracać zapisaną listę plików
                    operation.get(new TrackerOperationStrategy());
                    break;
                case "SET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy udostepnic listę udostępnianych plików");
                    outputStream.writeBytes("FILE SAVED" +'\n'); // TODO Zapisywać listę plików
                    operation.set(new TrackerOperationStrategy());
                    break;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");
                    outputStream.writeBytes("UNRECOGNIZED COMMAND" +'\n');
                    break;
            }
        }
    }
}
