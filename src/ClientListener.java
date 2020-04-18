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
    static List<Chatlog> chatlog = new ArrayList<Chatlog>();
    static List<String> sentMessages = new ArrayList<String>(), receivedMessages = new ArrayList<String>();
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

            System.out.println("Connectad!!! - " + client.isBound() + client.isConnected() + client.getInputStream().read());
            MainClass.newClient();
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

        buffer = ("{6969420-" + MainClass.users.size() + ":" + User.clientCount + ";6969420}").getBytes(); // fixar lite mer senare atag klienten nu vet hur många är online
        output.write(buffer);
        buffer = new byte[1000];
        read();
    }

    private static void read() throws Exception // Ska ändras så allt händer i Users klassen glöm inte fixa disconnect grejen
    {
        read = true;

        while (read)
        {
            if (input.read(buffer) == -1)
            {
                System.out.println("Disconnected");
                read = false;
                client.close();
                MainClass.sombodyDisconected(clientCount);
                break;
            }
            else if (false) //är till för att stoppa in funktioner som clienten kan skicka
            {

            }
            else
            {
                String message = new String(buffer);
                sentMessages.add(message);
                chatlog.add(new Chatlog(message, true));
                System.out.println("sent: " + message);
                buffer = new byte[1000];
            }
        }
    }
}