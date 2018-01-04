import java.io.DataOutputStream;
import java.io.IOException;

public class TrackerOperationStrategy implements OperationStrategy{
    DataOutputStream outputStream;

    public TrackerOperationStrategy(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void get() throws IOException {
        System.out.println("TrackerOperationStrategy");
        System.out.println("Wymiana list udostępnianych plików między hostami " +
                "-  chcemy wiedzieć gdzie (na jakim hoście) jakie pliki się znajdują, wraz z ich sumami kontrolnymi MD5");
        outputStream.writeBytes("FILES: []" +'\n'); // TODO Zwracać zapisaną listę plików
    }

    @Override
    public void set() throws IOException {
        System.out.println("TrackerOperationStrategy");
        System.out.println("Wymiana list udostępnianych plików między hostami " +
                "-  chcemy udostepnic listę udostępnianych plików");
        outputStream.writeBytes("FILE SAVED" +'\n'); // TODO Zapisywać listę plików
    }

    @Override
    public void push() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void pull() {
        System.out.println("Operation is now supported!");
    }
}
