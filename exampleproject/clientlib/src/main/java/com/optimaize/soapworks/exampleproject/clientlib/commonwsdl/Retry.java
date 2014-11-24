
package com.optimaize.soapworks.exampleproject.clientlib.commonwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retryInSeconds" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="retryType" type="{http://system.services.server.exampleproject.soapworks.optimaize.com/}retryType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retry", propOrder = {
    "retryInSeconds",
    "retryType"
})
public class Retry {

    protected Long retryInSeconds;
    protected RetryType retryType;

    /**
     * Gets the value of the retryInSeconds property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRetryInSeconds() {
        return retryInSeconds;
    }

    /**
     * Sets the value of the retryInSeconds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRetryInSeconds(Long value) {
        this.retryInSeconds = value;
    }

    /**
     * Gets the value of the retryType property.
     * 
     * @return
     *     possible object is
     *     {@link RetryType }
     *     
     */
    public RetryType getRetryType() {
        return retryType;
    }

    /**
     * Sets the value of the retryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetryType }
     *     
     */
    public void setRetryType(RetryType value) {
        this.retryType = value;
    }

}
