import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientListener implements Runnable
{
    static byte[] buffer = new byte[1000];
    static Socket client;
    static InputStream input;
    static OutputStream output;

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
        input.read(buffer);
        System.out.println(new String(buffer));
        MainClass.clients.add(client);
        }
        }