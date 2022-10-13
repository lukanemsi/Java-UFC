package ge.ufc.webapps.faults;

import javax.xml.ws.WebFault;

@WebFault
public class AccessForbiddenException extends RuntimeException
{
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
