package ge.ufc.webapps.faults;

public class AmountNotPositiveException extends Exception
{
    public AmountNotPositiveException() {
    }

    public AmountNotPositiveException(String message) {
        super(message);
    }

    public AmountNotPositiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountNotPositiveException(Throwable cause) {
        super(cause);
    }

    public AmountNotPositiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
