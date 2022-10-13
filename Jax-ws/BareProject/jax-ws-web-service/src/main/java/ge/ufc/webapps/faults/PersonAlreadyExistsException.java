package ge.ufc.webapps.faults;

import javax.xml.ws.WebFault;

@WebFault
public class PersonAlreadyExistsException extends Exception
{

    private static final long serialVersionUID = 1L;


    public PersonAlreadyExistsException() {
    }

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
