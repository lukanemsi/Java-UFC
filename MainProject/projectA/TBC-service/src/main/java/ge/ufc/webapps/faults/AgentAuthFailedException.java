package ge.ufc.webapps.faults;

public class AgentAuthFailedException extends Exception{
    public AgentAuthFailedException() {
    }

    public AgentAuthFailedException(String message) {
        super(message);
    }

    public AgentAuthFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgentAuthFailedException(Throwable cause) {
        super(cause);
    }

    public AgentAuthFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
