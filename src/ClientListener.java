import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
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

    public void run()
    {
        try
        {
            listen();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void listen() throws Exception
    {
        client = MainClass.server.accept();
        System.out.println("Connectad!!!");
        input = client.getInputStream();
        output = client.getOutputStream();
        read();
        MainClass.clients.add(client);
    }

    private static void read() throws Exception
    {
        read = true;

        while (read)
        {
            input.read(buffer);
            System.out.println(new String(buffer));
            buffer = new byte[1000];
        }
    }
}