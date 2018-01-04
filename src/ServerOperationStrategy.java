import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerOperationStrategy implements OperationStrategy{
    Connection connection;

    public ServerOperationStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void get() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void set() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void push() throws InterruptedException, IOException {
        System.out.println("ServerOperationStrategy");
        System.out.println("Otrzymam plik o podanej nazwie");
        TimeUnit.SECONDS.sleep(5);
        connection.sendMessage("Odebralem plik!");
        connection.close();
    }

    @Override
    public void pull() throws InterruptedException, IOException {
        System.out.println("ServerOperationStrategy");
        System.out.println("Mam udsotepnic plik o podanej nazwie");
        TimeUnit.SECONDS.sleep(5);
        connection.sendMessage("Udostepnilem plik!");
        connection.close();
    }
}
