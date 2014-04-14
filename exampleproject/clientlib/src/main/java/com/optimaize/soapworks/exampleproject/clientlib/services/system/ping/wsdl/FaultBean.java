
package com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.wsdl;

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
 *         &lt;element name="blame" type="{http://system.services.server.exampleproject.soapworks.optimaize.com/}blame" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="faultCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="problemReported" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="retryOtherServers" type="{http://system.services.server.exampleproject.soapworks.optimaize.com/}retry" minOccurs="0"/>
 *         &lt;element name="retrySameServer" type="{http://system.services.server.exampleproject.soapworks.optimaize.com/}retry" minOccurs="0"/>
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
    "blame",
    "errorCode",
    "faultCause",
    "message",
    "problemReported",
    "retryOtherServers",
    "retrySameServer"
})
public class FaultBean {

    protected Blame blame;
    protected int errorCode;
    protected String faultCause;
    protected String message;
    protected boolean problemReported;
    protected Retry retryOtherServers;
    protected Retry retrySameServer;

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
     * Gets the value of the errorCode property.
     * 
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     */
    public void setErrorCode(int value) {
        this.errorCode = value;
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
     * Gets the value of the problemReported property.
     * 
     */
    public boolean isProblemReported() {
        return problemReported;
    }

    /**
     * Sets the value of the problemReported property.
     * 
     */
    public void setProblemReported(boolean value) {
        this.problemReported = value;
    }

    /**
     * Gets the value of the retryOtherServers property.
     * 
     * @return
     *     possible object is
     *     {@link Retry }
     *     
     */
    public Retry getRetryOtherServers() {
        return retryOtherServers;
    }

    /**
     * Sets the value of the retryOtherServers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Retry }
     *     
     */
    public void setRetryOtherServers(Retry value) {
        this.retryOtherServers = value;
    }

    /**
     * Gets the value of the retrySameServer property.
     * 
     * @return
     *     possible object is
     *     {@link Retry }
     *     
     */
    public Retry getRetrySameServer() {
        return retrySameServer;
    }

    /**
     * Sets the value of the retrySameServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Retry }
     *     
     */
    public void setRetrySameServer(Retry value) {
        this.retrySameServer = value;
    }

}
