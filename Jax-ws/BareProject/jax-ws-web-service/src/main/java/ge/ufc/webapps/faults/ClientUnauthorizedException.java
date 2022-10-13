package ge.ufc.webapps.faults;

import javax.xml.ws.WebFault;

@WebFault
public class ClientUnauthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ClientUnauthorizedException() {
    }

    public ClientUnauthorizedException(String message) {
        super(message);
    }
}
