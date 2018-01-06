import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerOperationStrategy implements OperationStrategy{
    Connection connection;

    public ServerOperationStrategy(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void get() throws IOException {
        System.out.println("Operation is now supported!");
        System.out.println("Udostepniam liste plikow");
        final File folder = new File("/1");
        System.out.println(getFileList());
        connection.sendMessage(getFileList());
    }

    private String getFileList() {
        File folder = new File("1");
        StringBuilder listOfFiles = new StringBuilder();
        for (File file : folder.listFiles()) {
            listOfFiles.append(file.getName());
            listOfFiles.append(" ");
        }
        return String.valueOf(listOfFiles);
    }

    @Override
    public void register() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void push() throws InterruptedException, IOException {
        PushOperationThread pushOperationThread = new PushOperationThread();
    }

    @Override
    public void pull() throws InterruptedException, IOException {
        PushOperationThread pushOperationThread = new PushOperationThread();
    }

    class PushOperationThread {
        private Thread thread;

        public PushOperationThread() {
            thread = new Thread() {
                public void run() {
                    try {
                        System.out.println("ServerOperationStrategy");
                        System.out.println("Mam udsotepnic plik o podanej nazwie");
                        TimeUnit.SECONDS.sleep(5);
                        connection.sendMessage("Udostepnilem plik!");
                        connection.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    class PullOperationThread {
        private Thread thread;

        public PullOperationThread() {
            thread = new Thread() {
                public void run() {
                    try {
                        System.out.println("ServerOperationStrategy");
                        System.out.println("Otrzymam plik o podanej nazwie");
                        TimeUnit.SECONDS.sleep(5);
                        connection.sendMessage("Odebralem plik!");
                        connection.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
}
