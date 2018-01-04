import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TrackerOperationStrategy implements OperationStrategy{
    DataOutputStream outputStream;

    public TrackerOperationStrategy(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void get() throws IOException {
        GetOperationThread getOperationThread = new GetOperationThread();
    }

    @Override
    public void set() throws IOException {
        SetOperationThread getOperationThread = new SetOperationThread();
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
                        outputStream.writeBytes("FILE SAVED" +'\n'); // TODO Zapisywać listę plików
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

    class SetOperationThread {
        private Thread thread;

        public SetOperationThread() {
            thread = new Thread() {
                public void run() {
                    System.out.println("TrackerOperationStrategy");
                    System.out.println("Wymiana list udostępnianych plików między hostami " +
                            "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        outputStream.writeBytes("FILES: []" +'\n'); // TODO Zwracać zapisaną listę plików
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
}
