
package ge.ufc.webservices.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdatePersonResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatePersonResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UpdatePersonResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatePersonResponse", propOrder = {
    "updatePersonResult"
})
public class UpdatePersonResponse {

    @XmlElement(name = "UpdatePersonResult")
    protected boolean updatePersonResult;

    /**
     * Gets the value of the updatePersonResult property.
     * 
     */
    public boolean isUpdatePersonResult() {
        return updatePersonResult;
    }

    /**
     * Sets the value of the updatePersonResult property.
     * 
     */
    public void setUpdatePersonResult(boolean value) {
        this.updatePersonResult = value;
    }

}
