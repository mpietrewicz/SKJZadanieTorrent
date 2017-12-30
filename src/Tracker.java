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

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            InputStreamReader inputStream = new InputStreamReader(connectionSocket.getInputStream());
            BufferedReader inputStreamBufferedReader = new BufferedReader(inputStream);

            String command = inputStreamBufferedReader.readLine();
            switch (command) {
                case "GET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");
                    break;
                case "SET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy udostepnic listę udostępnianych plików");
                    break;
                default:
                    System.out.println("UNRECOGNIZED COMMAND");;
                    break;
            }
        }
    }
}
