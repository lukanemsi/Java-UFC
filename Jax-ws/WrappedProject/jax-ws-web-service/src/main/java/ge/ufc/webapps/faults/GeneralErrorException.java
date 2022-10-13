package ge.ufc.webapps.faults;

import javax.xml.ws.WebFault;

@WebFault
public class GeneralErrorException extends RuntimeException{

    private static final long serialVersionUID = 1L;


    public GeneralErrorException() {
    }

    public GeneralErrorException(String message) {
        super(message);
    }
}
