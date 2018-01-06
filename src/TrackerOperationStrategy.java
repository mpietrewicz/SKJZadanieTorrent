import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TrackerOperationStrategy implements OperationStrategy{
    Connection connection;
    Command command;
    UserRegister userRegister = UserRegister.INSTANCE;

    public TrackerOperationStrategy(Connection connection) {
        this.connection = connection;
    }

    public TrackerOperationStrategy(Command command, Connection connection) {
        this.command = command;
        this.connection = connection;
    }

    @Override
    public void get() throws IOException {
        GetOperationThread getOperationThread = new GetOperationThread();
    }

    @Override
    public void register() throws IOException {
        RegisterOperationThread registerOperationThread = new RegisterOperationThread();
    }

    @Override
    public void push() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void pull() {
        System.out.println("Operation is now supported!");
    }

    class GetOperationThread {
        private Thread thread;

        public GetOperationThread() {
            thread = new Thread() {
                public void run() {
                    System.out.println("TrackerOperationStrategy");
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy udostepnic listę udostępnianych plików");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        connection.sendMessage("FILE SAVED"); // TODO Zapisywać listę plików
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    class RegisterOperationThread {
        private Thread thread;

        public RegisterOperationThread() {
            thread = new Thread() {
                public void run() {
                System.out.println("TrackerOperationStrategy");
                System.out.println("Rejestracja uzytkownika");
                userRegister.addUser(command.getArgs().get(0));
                System.out.println("Zarejestrowano Użytkownika o id: " +command.getArgs().get(0));
                }
            };
            thread.start();
        }
    }
}
