package App.Operation;

import App.Comunication.Command;
import App.Comunication.Connection;
import App.Repository.UserRegister;

import java.io.IOException;

public class TrackerOperation implements OperationStrategy{
    Connection connection;
    Command command;
    UserRegister userRegister = UserRegister.INSTANCE;

    public TrackerOperation(Connection connection) {
        this.connection = connection;
    }

    public TrackerOperation(Command command, Connection connection) {
        this.command = command;
        this.connection = connection;
    }

    @Override
    public void get() throws IOException {
        GetOperationThread getOperationThread = new GetOperationThread();
    }

    @Override
    public void register() throws IOException {
        RegisterOperationThread registerOperationThread = new RegisterOperationThread();
    }

    @Override
    public void push() {
        System.out.println("App.Operation.Operation is now supported!");
    }

    @Override
    public void pull() {
        System.out.println("App.Operation.Operation is now supported!");
    }

    class GetOperationThread {
        private Thread thread;

        public GetOperationThread() {
            thread = new Thread() {
                public void run() {
                    System.out.println("App.Operation.TrackerOperation");
                    System.out.println("Pobieram liste plikow od wszystkich hostow: ");
                    try {
                        StringBuilder allFiles = new StringBuilder();
                        allFiles.append("");
                        for (String userId : userRegister.getUsers()) {
                            Connection connectionToHost = new Connection("127.0.0.1", 10000+Integer.parseInt(userId));
                            connectionToHost.sendMessage("GET");
                            String response = connectionToHost.readMessage();
                            System.out.println(response);
                            allFiles.append(response);
                            allFiles.append(" ");
                            connectionToHost.close();
                       }
                        connection.sendMessage(String.valueOf(allFiles));
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    class RegisterOperationThread {
        private Thread thread;

        public RegisterOperationThread() {
            thread = new Thread() {
                public void run() {
                System.out.println("App.Operation.TrackerOperation");
                System.out.println("Rejestracja uzytkownika");
                userRegister.addUser(command.getArgs().get(0));
                System.out.println("Zarejestrowano Użytkownika o id: " +command.getArgs().get(0));
                }
            };
            thread.start();
        }
    }
}
