import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientListener implements Runnable
{
    byte[] buffer = new byte[1000];
    Socket client;
    InputStream input;
    OutputStream output;
    List<Chatlog> chatlog = new ArrayList<Chatlog>();
    List<String> sentMessages = new ArrayList<String>(), receivedMessages = new ArrayList<String>();
    String username;
    int clientCount;
    boolean read = true;


    ClientListener(int clientCount)
    {
        this.clientCount = clientCount;
    }

    public void run()
    {
        try
        {
            System.out.println("tata");
            client = MainClass.server.accept();

            System.out.println("Connectad!!! - ");
            username = clientCount + ";";
            clientSetup();
            MainClass.newClient(username);
            read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void clientSetup() throws Exception
    {
        input = client.getInputStream();
        output = client.getOutputStream();
    }

    private void read() throws Exception // Ska ändras så allt händer i Users klassen glöm inte fixa disconnect grejen
    {

        while (read)
        {
            if (input.read(buffer) == -1 || !new String(buffer).substring(0,8).equals("{6969420"))
            {
                disconnect();
            }
            else
            {
                String message = new String(buffer);
                if (message.charAt(8) == '#')
                {
                    System.out.println(username);
                    username = message.substring(9, message.indexOf(';'));
                    System.out.println("namnet: " + username);
                }
                else if (message.charAt(8) == '?')
                {
                    String userList = "";
                    for (int i = 0; i < MainClass.users.size() - 1; i++)
                    {
                        if (MainClass.users.get(i).username.indexOf(';') == -1)
                        {
                            userList+=MainClass.users.get(i).username + ":";
                        }
                    }
                    sendMessage(userList, '?');
                }
                else if (message.charAt(8) == '+')
                {
                    String temp = message.substring(9, message.indexOf(';'));
                    sentMessages.add(message);
                    chatlog.add(new Chatlog(message, true));
                    System.out.println("sent: " + temp);
                }
                else if (message.charAt(8) == '¤')
                {
                    disconnect();
                }
            }
            buffer = new byte[1000];
        }
    }

    public void disconnect() throws Exception
    {
        System.out.println("Disconnected");
        read = false;
        client.close();
        MainClass.sombodyDisconected(username);
    }

    public void sendMessage(String message, char messageType)
    {
        try
        {
            if (messageType == '?')
            {
                String temp = "{6969420?" + message + ";";
                buffer =  temp.getBytes();
                output.write(buffer);
                buffer = new byte[1000];
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}