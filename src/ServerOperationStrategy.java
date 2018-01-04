public class ServerOperationStrategy implements OperationStrategy{
    @Override
    public void get() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void set() {
        System.out.println("Operation is now supported!");
    }

    @Override
    public void push() {
        System.out.println("ServerOperationStrategy");
    }

    @Override
    public void pull() {
        System.out.println("ServerOperationStrategy");
    }
}
