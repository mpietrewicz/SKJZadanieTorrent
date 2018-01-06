import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client implements Runnable {
    int id;

    public Client(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Connection connection;
        String port;
        Operation operation = new Operation();

        BufferedReader userCommandLine = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                Command command = new Command(userCommandLine.readLine());

                switch (command.getOperation()) {
                    case "GET":
                        System.out.println("Wymiana list udostępnianych plików między hostami " +
                                "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");
                        port = "10000";
                        operation.get(new ClientOperationStrategy(command, port));
                        break;
                    case "PULL":
                        System.out.println("ściągamy z wybranego hosta plik o zadanej nazwie ");
                        System.out.print("Podaj nazwe hosta z ktorego ma być pobrany plik: ");
                        port = userCommandLine.readLine();
                        operation.pull(new ClientOperationStrategy(command, port));
                        break;
                    case "PUSH":
                        System.out.println("wrzucamy na wybrany host pliku o zadanej nazwie");
                        System.out.print("Podaj nazwe hosta do ktorego ma być wysłany plik: ");
                        port = userCommandLine.readLine();
                        operation.push(new ClientOperationStrategy(command, port));
                        break;
                    case "QUIT":
                        return;
                    default:
                        System.out.println("UNRECOGNIZED COMMAND");
                        ;
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
