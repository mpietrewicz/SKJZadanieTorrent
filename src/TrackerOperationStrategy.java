import java.io.DataOutputStream;

public class TrackerOperationStrategy implements OperationStrategy{

    @Override
    public void get() {
        System.out.println("TrackerOperationStrategy");
    }

    @Override
    public void set() {
        System.out.println("TrackerOperationStrategy");
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
