import java.io.IOException;

public interface OperationStrategy {

    void get() throws IOException;

    void set() throws IOException;

    void push() throws InterruptedException, IOException;

    void pull() throws InterruptedException, IOException;
}
