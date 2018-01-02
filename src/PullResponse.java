import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PullResponse implements Runnable {
    Connection connection;
    Command command;

    public PullResponse(Connection connection, Command command) {
        this.connection = connection;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            System.out.println("Mam udsotepnic plik o podanej nazwie");
            TimeUnit.SECONDS.sleep(20);
            connection.sendMessage("Udostepnilem plik!");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
