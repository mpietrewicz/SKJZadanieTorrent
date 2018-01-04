public class Operation {

    public void get(OperationStrategy operationStrategy) {
        operationStrategy.get();
    }

    public void set(OperationStrategy operationStrategy) {
        operationStrategy.set();
    }

    public void push(OperationStrategy operationStrategy) {
        operationStrategy.push();
    }

    public void pull(OperationStrategy operationStrategy) {
        operationStrategy.pull();
    }
}
