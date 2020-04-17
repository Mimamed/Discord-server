import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientListener implements Runnable
{
    static byte[] buffer = new byte[1000];
    static Socket client;
    static InputStream input;
    static OutputStream output;
    static List<String> chatlog = new ArrayList<String>();
    static boolean read = false;
    static int clientCount;

    ClientListener(int clientCount)
    {
        if (clientCount < 0 || clientCount > MainClass.serverSize)
        {
            this.clientCount = clientCount;
        }
    }
    public void run()
    {
        try
        {
            client = MainClass.server.accept();

            System.out.println("Connectad!!! - ");
            if (clientCount + 1 <= MainClass.serverSize)
            {
                MainClass.newClient();
            }
            clientSetup();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void clientSetup() throws Exception
    {
        input = client.getInputStream();
        output = client.getOutputStream();
        MainClass.users.add(new User(client));
        MainClass.users.get(clientCount).receiveMessage("noobie");
        read();
    }

    private static void read() throws Exception // Ska ändras så allt händer i Users klassen glöm inte fixa disconnect grejen
    {
        read = true;

        while (read)
        {
            if (!client.isConnected())
            {
                read = false;
                break;
            }

            input.read(buffer);
            String message = new String(buffer);
            MainClass.users.get(clientCount).receiveMessage(message.substring(0, 10));
            System.out.println(message);
            buffer = new byte[1000];
        }
    }
}