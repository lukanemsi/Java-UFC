package ge.ufc.webapps.faults;

public class AgentAccessDeniedException extends Exception
{
    public AgentAccessDeniedException() {
    }

    public AgentAccessDeniedException(String message) {
        super(message);
    }

    public AgentAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgentAccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AgentAccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
