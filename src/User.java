import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User
{
    static String ipAddress;
    static List<String> receivedMessages = new ArrayList<String>();
    static List<String> sentMessages = new ArrayList<String>();
    static int clientCount;

    User(int clientCount)
    {
        this.clientCount = clientCount;
        System.out.println("skapad");
    }

    public static void receiveMessage(String message)
    {
        receivedMessages.add(message);
        System.out.println("Sends - " + message);
        //ta emot medelandet
    }

    public static void sendsMessage(String message)
    {
        sentMessages.add(message);
        System.out.println("receave - " + message);
        //skicka medelandet
    }

}