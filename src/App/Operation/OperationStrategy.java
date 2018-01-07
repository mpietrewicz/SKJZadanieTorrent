package App.Operation;

import java.io.IOException;

public interface OperationStrategy {

    void get() throws IOException;

    void register() throws IOException;

    void push() throws InterruptedException, IOException;

    void pull() throws InterruptedException, IOException;
}
