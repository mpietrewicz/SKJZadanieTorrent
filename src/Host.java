import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Host {
    public static void main(String[] args) throws IOException {
        int id = Integer.parseInt(args[0]);
        if(id > 0 && id<100) {
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
                    break;
                case "SET":
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy udostepnic listę udostępnianych plików");
                    break;
                case "PULL":
                    System.out.println("ściągamy z wybranego hosta plik o zadanej nazwie ");;
                    break;
                case "PUSH":
                    System.out.println("wrzucamy na wybrany host pliku o zadanej nazwie");;
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
