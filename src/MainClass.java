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
    static List<ClientListener> users = new ArrayList<ClientListener>();

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
        users.add(new ClientListener(users.size()));
        connections.add(new Thread(users.get(0)));
        connections.get(0).start();
    }

    public static void newClient()
    {
        if (users.size() < serverSize)
        {
            users.add(new ClientListener(users.size()));
            connections.add(new Thread(users.get(users.size() - 1)));
            connections.get(connections.size() - 1).start();
        }
    }

    public static void sombodyDisconected(int clientCount)
    {
        for(int i = 0; i < users.get(clientCount).chatlog.size(); i++)
        {
            System.out.println("-----: " + users.get(clientCount).chatlog.get(i).getMessage());
        }

        users.get(clientCount).read = false;
        try
        {
            users.get(clientCount).client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        users.remove(clientCount);
        connections.remove(clientCount);

        for(int i = clientCount; i < users.size(); i++)
        {
            users.get(i).clientCount--;
        }

    }
}