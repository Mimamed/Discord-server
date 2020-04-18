public class Chatlog
{

    private String message;
    private boolean sent;

    Chatlog(String message, boolean sent)
    {
        this.message = message;
        this.sent = sent;
    }

    public String getMessage()
    {
        return message;
    }

    public boolean getSent()
    {
        return sent;
    }

}
