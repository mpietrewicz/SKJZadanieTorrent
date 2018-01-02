import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PushResponse implements Runnable {
    Connection connection;
    String command;

    public PushResponse(Connection connection, String command) {
        this.connection = connection;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            System.out.println("Otrzymam plik o podanej nazwie");
            TimeUnit.SECONDS.sleep(20);
            connection.sendMessage("Odebralem plik!");
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}