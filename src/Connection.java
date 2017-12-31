import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private Socket clientSocket;
    private InputStreamReader inputStream;
    private BufferedReader inputStreamBufferedReader;
    private DataOutputStream outputStream;

    public Connection(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
        this.inputStream = new InputStreamReader(clientSocket.getInputStream());
        this.inputStreamBufferedReader = new BufferedReader(inputStream);
    }

    public void sendMessage(String message) throws IOException {
        outputStream.writeBytes(message +'\n');
    }

    public String readMessage() throws IOException {
        return inputStreamBufferedReader.readLine();
    }

}
