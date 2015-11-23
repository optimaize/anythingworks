
package com.optimaize.anythingworks.exampleproject.clientlib.soap.commonwsdl;

import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for faultBean complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="faultBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blame" type="{http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/}blame" minOccurs="0"/>
 *         &lt;element name="faultCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incidentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retryOtherLocations" type="{http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/}retry" minOccurs="0"/>
 *         &lt;element name="retrySameLocation" type="{http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/}retry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "faultBean", propOrder = {
        "applicationErrorCode",
        "blame",
        "faultCause",
        "incidentId",
        "message",
        "retryOtherLocations",
        "retrySameLocation"
})
public class FaultBean {

    protected String applicationErrorCode;
    protected Blame blame;
    protected String faultCause;
    protected String incidentId;
    protected String message;
    protected Retry retryOtherLocations;
    protected Retry retrySameLocation;

    /**
     * Gets the value of the applicationErrorCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getApplicationErrorCode() {
        return applicationErrorCode;
    }

    /**
     * Sets the value of the applicationErrorCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setApplicationErrorCode(String value) {
        this.applicationErrorCode = value;
    }

    /**
     * Gets the value of the blame property.
     *
     * @return
     *     possible object is
     *     {@link Blame }
     *
     */
    public Blame getBlame() {
        return blame;
    }

    /**
     * Sets the value of the blame property.
     *
     * @param value
     *     allowed object is
     *     {@link Blame }
     *
     */
    public void setBlame(Blame value) {
        this.blame = value;
    }

    /**
     * Gets the value of the faultCause property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFaultCause() {
        return faultCause;
    }

    /**
     * Sets the value of the faultCause property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFaultCause(String value) {
        this.faultCause = value;
    }

    /**
     * Gets the value of the incidentId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIncidentId() {
        return incidentId;
    }

    /**
     * Sets the value of the incidentId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIncidentId(String value) {
        this.incidentId = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the retryOtherLocations property.
     *
     * @return
     *     possible object is
     *     {@link Retry }
     *
     */
    public Retry getRetryOtherLocations() {
        return retryOtherLocations;
    }

    /**
     * Sets the value of the retryOtherLocations property.
     *
     * @param value
     *     allowed object is
     *     {@link Retry }
     *
     */
    public void setRetryOtherLocations(Retry value) {
        this.retryOtherLocations = value;
    }

    /**
     * Gets the value of the retrySameLocation property.
     *
     * @return
     *     possible object is
     *     {@link Retry }
     *
     */
    public Retry getRetrySameLocation() {
        return retrySameLocation;
    }

    /**
     * Sets the value of the retrySameLocation property.
     *
     * @param value
     *     allowed object is
     *     {@link Retry }
     *
     */
    public void setRetrySameLocation(Retry value) {
        this.retrySameLocation = value;
    }

}
