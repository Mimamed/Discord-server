import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User
{
    static String ipAddress;
    static List<String> receivedMessages = new ArrayList<String>();
    static List<String> sentMessages = new ArrayList<String>();

    User(Socket client)
    {
        System.out.println("skapad");
    }

    public static void receiveMessage(String message)
    {
        receivedMessages.add(message);
        System.out.println(message);
        //ta emot medelandet
    }

    public static void sendsMessage(String message)
    {
        sentMessages.add(message);
        //skicka medelandet
    }

}