import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainClass
{

    static int port = 7777, serverSize = 100;
    static ServerSocket server;
    static List<User> accounts = new ArrayList<User>();
    static List<Thread> connections = new ArrayList<Thread>();
    static List<ClientListener> users = new ArrayList<ClientListener>();
    static byte[] buffer = new byte[1000];

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

    public static void broadcast(String message, char messageType, String username)
    {
        String temp = "{6969420?noob";
        if (messageType == '?')
        {

        }

        for (int i = 0; i < users.size() - 1; i++)
        {
            buffer = (message).getBytes(); // fixar lite mer senare atag klienten nu vet hur många är online
            try
            {
                users.get(i).output.write(buffer);
                System.out.println("teeeeeest");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            buffer = new byte[1000];
        }
    }

    public static void newClient(String username)
    {
        //broadcast(Integer.toString(users.size() - 1),'?', username);//------------------- fixa
        if (users.size() < serverSize)
        {
            users.add(new ClientListener(users.size()));
            connections.add(new Thread(users.get(users.size() - 1)));
            connections.get(connections.size() - 1).start();
        }
    }

    public static void sombodyDisconected(String username)
    {

        System.out.println("förstöra: " + username);

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).username.equals(username))
            {
                users.get(i).read = false;
                users.remove(i);
                connections.remove(i);
            }
        }

        //broadcast("{6969420-" + (users.size() - 1) + ":" + username + ";");

    }
}