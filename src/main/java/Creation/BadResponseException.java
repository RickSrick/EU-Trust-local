package Creation;

public class BadResponseException extends Exception{
    public BadResponseException () { super(); }
    public BadResponseException(String message) {
        super(message);
    }

}
