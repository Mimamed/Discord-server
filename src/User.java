import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User
{
    static List<String> receivedMessages = new ArrayList<String>();
    static List<String> sentMessages = new ArrayList<String>();
    static String username;

    User(String username)
    {
        this.username = username;
        System.out.println("skapad");
    }

    public static void receiveMessage(String message) // gör så att socketarna sänder det som saknas
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