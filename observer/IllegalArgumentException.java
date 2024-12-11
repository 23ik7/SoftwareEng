package observer;

public class IllegalArgumentException extends NewsSpreaderException{
    public IllegalArgumentException(String source) {
        super("Argumentation failure: " + source);
    }
}
