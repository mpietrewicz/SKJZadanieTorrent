import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.concurrent.TimeUnit;

public class Connection implements Runnable{
    static int portStartNumber = 10000;
    static int portEndNumber = 10100;

    private Socket socket;
    private InputStreamReader inputStream;
    private BufferedReader inputStreamBufferedReader;
    private DataOutputStream outputStream;

    private String command;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.inputStream = new InputStreamReader(socket.getInputStream());
        this.inputStreamBufferedReader = new BufferedReader(inputStream);
    }

    public Connection(String ip, int port) throws IOException {
        if(!isPortValid(port)) {
            throw new ExportException("Port number must be in range: 10001-10100");
        }
        this.socket = new Socket(ip, port);
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.inputStream = new InputStreamReader(socket.getInputStream());
        this.inputStreamBufferedReader = new BufferedReader(inputStream);
    }

    public Connection(String ip, String port) throws IOException {
        this(ip, Integer.parseInt(port));
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeBytes(message +'\n');
    }

    public String readMessage() throws IOException {
        return inputStreamBufferedReader.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }

    public static boolean isPortValid(int port) {
        if(port > portStartNumber && port<portEndNumber) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isPortValid(String port) {
        return isPortValid(Integer.parseInt(port));
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println("Mam udsotepnic plik o podanej nazwie");
        try {
            TimeUnit.SECONDS.sleep(45);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            sendMessage("Udostepnilem plik!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
