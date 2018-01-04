public class ClientOperationStrategy implements OperationStrategy {
    @Override
    public void get() {
        System.out.println("ClientOperationStrategy");
    }

    @Override
    public void set() {
        System.out.println("ClientOperationStrategy");
    }

    @Override
    public void push() {
        System.out.println("ClientOperationStrategy");
    }

    @Override
    public void pull() {
        System.out.println("ClientOperationStrategy");
    }
}
