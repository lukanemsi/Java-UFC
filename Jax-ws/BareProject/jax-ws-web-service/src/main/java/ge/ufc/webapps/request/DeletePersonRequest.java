package ge.ufc.webapps.request;


import ge.ufc.webapps.model.Auth;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class DeletePersonRequest {
    @XmlElement(required = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    private Auth auth;
    public Auth getAuth() {
        return auth;
    }
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
