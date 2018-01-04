import java.io.IOException;

public class Operation {

    public void get(OperationStrategy operationStrategy) throws IOException {
        operationStrategy.get();
    }

    public void set(OperationStrategy operationStrategy) throws IOException {
        operationStrategy.set();
    }

    public void push(OperationStrategy operationStrategy) throws IOException, InterruptedException {
        operationStrategy.push();
    }

    public void pull(OperationStrategy operationStrategy) throws IOException, InterruptedException {
        operationStrategy.pull();
    }
}
