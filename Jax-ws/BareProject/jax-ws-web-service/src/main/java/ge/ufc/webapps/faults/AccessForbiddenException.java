package ge.ufc.webapps.faults;

import javax.xml.ws.WebFault;

@WebFault
public class AccessForbiddenException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
