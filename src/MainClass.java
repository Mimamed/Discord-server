import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainClass
{

    static int port = 7777, serverSize = 100;
    static ServerSocket server;
    static List<Socket> clients = new ArrayList<Socket>();
    static List<Thread> connections = new ArrayList<Thread>();
    static List<User> users = new ArrayList<User>();

    public static void main(String[] args)
    {

        try
        {
            setupServer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void setupServer() throws Exception
    {
        server = new ServerSocket(port);
        connections.add(new Thread(new ClientListener(0)));
        connections.get(0).start();
    }

    public static void newClient()
    {
        connections.add(new Thread(new ClientListener(connections.size())));
        connections.get(connections.size() - 1).start();

    }
}