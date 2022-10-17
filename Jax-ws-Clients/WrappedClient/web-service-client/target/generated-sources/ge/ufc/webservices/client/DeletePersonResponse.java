
package ge.ufc.webservices.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeletePersonResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeletePersonResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DeletePersonResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeletePersonResponse", propOrder = {
    "deletePersonResult"
})
public class DeletePersonResponse {

    @XmlElement(name = "DeletePersonResult")
    protected boolean deletePersonResult;

    /**
     * Gets the value of the deletePersonResult property.
     * 
     */
    public boolean isDeletePersonResult() {
        return deletePersonResult;
    }

    /**
     * Sets the value of the deletePersonResult property.
     * 
     */
    public void setDeletePersonResult(boolean value) {
        this.deletePersonResult = value;
    }

}
